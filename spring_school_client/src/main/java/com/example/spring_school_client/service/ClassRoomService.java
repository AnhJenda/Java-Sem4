package com.example.spring_school_client.service;

import com.example.spring_school_client.config.ServiceProperties;
import com.example.spring_school_client.dto.ClassDto;
import com.example.spring_school_client.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 7/31/2023
    Project: Spring_school_client
*/
@Component
public class ClassroomService {
    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    ServiceProperties serviceProperties;

    public List<ClassDto> getListClassroom(ClassDto criteria){
        String url =  serviceProperties.getBaseUrl() + serviceProperties.getClassroomListUrl();
        ParameterizedTypeReference<List<ClassDto>> responseType = new ParameterizedTypeReference<List<ClassDto>>() {};
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json"); // set header
        httpHeaders.add("Authorization", "Bearer + token"); // set token

        HttpEntity<ClassDto> entity = new HttpEntity<>(criteria, httpHeaders);

        ResponseEntity<List<ClassDto>> response = restTemplate.exchange(url, HttpMethod.POST,entity, responseType);
        return response.getBody();
    }

    public ClassDto getClassroom(Long id){
        String url =  serviceProperties.getBaseUrl() + serviceProperties.getClassroom();
        ParameterizedTypeReference<ClassDto> responseType = new ParameterizedTypeReference<ClassDto>() {};
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json"); // set header
        httpHeaders.add("Authorization", "Bearer + token"); // set token

        HttpEntity<ClassDto> entity = new HttpEntity<>(httpHeaders);
        // Thêm id vào URL dưới dạng tham số query parameter
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("id", id);

        ResponseEntity<ClassDto> response = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,entity, responseType);
        return response.getBody();
    }

    public ClassDto save(ClassDto classDto) {
        String url = serviceProperties.getBaseUrl() + serviceProperties.getSaveClassroom();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + "token");

        HttpEntity<ClassDto> requestEntity = new HttpEntity<>(classDto, headers);

        ResponseEntity<ClassDto> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ClassDto.class);

        ClassDto result = response.getBody();

        if (classDto == null) {

        }

        return result;
    }

    public void delete(Long id){
        String url = serviceProperties.getBaseUrl() + serviceProperties.getDeleteClassroom() + "?id=" + id;

        HttpHeaders headers = new HttpHeaders();

        headers.add("Content-Type", "application/json");
        headers.add("Authorization", "Bearer " + "token");

        HttpEntity<ClassDto> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<ClassDto> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ClassDto.class);

        HttpStatus httpStatusCode = response.getStatusCode();
        if (httpStatusCode == HttpStatus.OK) {
            System.out.println("Deleted");
        } else {
            System.out.println("Error");
        }
    }

//    public ClassDto save(ClassDto classDto) {
//        String url = serviceProperties.getBaseUrl() + serviceProperties.getClazz() + serviceProperties.getSave();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Authorization", "Bearer " + "token");
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        String classDtoJson;
//        try {
//            classDtoJson = objectMapper.writeValueAsString(classDto);
//        } catch (JsonProcessingException e) {
//            // Xử lý lỗi nếu có
//            e.printStackTrace();
//            return null;
//        }
//
//        HttpEntity<String> requestEntity = new HttpEntity<>(classDtoJson, headers);
//
//        ResponseEntity<ClassDto> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, ClassDto.class);
//
//        ClassDto result = response.getBody();
//
//        if (classDto == null) {
//            // Xử lý kết quả trả về từ server (result)
//            System.out.println(result);
//}
////
////        return result;
////    }
//
//
////    @Autowired
////    private SchoolMapper mapper;
////    @Autowired
////    private ClassRepository repository;
////    @Autowired
////    private ClassSpecification specification;
////
////    @Override
////    public List<ClassDto> gets(ClassDto criteria) {
////        if (criteria.getPageNumber() < 0  criteria.getPageSize() < 0) {
////            throw new BusinessException("201", "invalid param");
////        }
////        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
////        Page<Class> classes = repository.findAll(specification.filter(criteria), pageable);
////        return classes.getContent()
////                .stream()
////                .map(mapper::entityToDtoClass)
////                .collect(Collectors.toList());
////    }
////
////    @Override
////    public ClassDto getById(Long id) {
////        if (id == null || id < 0) {
////            throw new BusinessException("201", "invalid param");
////        }
////        return mapper.entityToDtoClass(repository.getById(id));
////    }
////
////    // cách 1: dùng unique trên field name và code
//////    @Override
//////    public ClassDto save(ClassDto classDto) {
//////       try
//////       {
//////           Class clazz = mapper.dtoToEntityClass(classDto);
//////           //boolean result = repository.existsByNameOrCode(classDto.getName(), classDto.getCode()); && !result
//////           if (!Objects.isNull(clazz) && validDate(classDto.getStartTime(),classDto.getEndTime())) {
//////               Class returnEntity = repository.save(clazz);
//////               return mapper.entityToDtoClass(returnEntity);
//////           } else {
//////               throw new BusinessException("201", "invalid param");
//////           }
//////       }catch (Exception e) {
//////           throw new BusinessException("201", "invalid param");
//////       }
//////    }
////
////    // cách 2: dùng query
////    @Override
////    public ClassDto save(ClassDto classDto) {
////        Class clazz = mapper.dtoToEntityClass(classDto);
////        boolean result = repository.existsByNameOrCode(classDto.getName(), classDto.getCode());
////        if (!Objects.isNull(clazz) && validDate(classDto.getStartTime(), classDto.getEndTime()) && !result) {
////            Class returnEntity = repository.save(clazz);
////            return mapper.entityToDtoClass(returnEntity);
////        } else {
////            throw new BusinessException("201", "invalid param");
////        }
////
////    }
//
//    private boolean validDate(Date start, Date end) {
//        LocalDate currentDate = LocalDate.now(); // Lấy ngày hiện tại từ LocalDate
//
//        LocalDate startDate = start.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Chuyển đổi Date sang LocalDate
//        LocalDate endDate = end.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(); // Chuyển đổi Date sang LocalDate
//
//        if (startDate.isAfter(currentDate) && endDate.isAfter(startDate)) {
//            return true;
//        }
//
//        return false;
//    }
//}
}
