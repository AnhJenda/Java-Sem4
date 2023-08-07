package com.example.spring_school.service.impl;

import com.example.spring_school.dto.ClassDto;
import com.example.spring_school.entity.Class;
import com.example.spring_school.exception.BusinessException;
import com.example.spring_school.mapper.ClassroomMapper;
import com.example.spring_school.repo.ClassRepository;
import com.example.spring_school.service.ClassService;
import com.example.spring_school.spec.ClassSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    private ClassroomMapper mapper;
    @Autowired
    private ClassRepository repository;
    @Autowired
    private ClassSpecification specification;

    @Override
    public List<ClassDto> gets(ClassDto criteria) {
        if (criteria.getPageNumber() < 0 || criteria.getPageNumber() == null
                || criteria.getPageSize() < 0 || criteria.getPageSize() == null) {
            throw new BusinessException("201", "invalid param");
        }
        Pageable pageable = PageRequest.of(criteria.getPageNumber(), criteria.getPageSize());
        Page<Class> classes = repository.findAll(specification.filter(criteria), pageable);
        return classes.getContent()
                .stream()
                .map(mapper::entityToDtoClass)
                .collect(Collectors.toList());
    }

    @Override
    public ClassDto getById(Long id) {
        if (id == null || id < 0) {
            throw new BusinessException("201", "invalid param");
        }
        Class cl = repository.getById(id);
        ClassDto classDto = mapper.entityToDtoClass(repository.getById(id));
        return mapper.entityToDtoClass(repository.getById(id));
    }

    // cách 1: dùng unique trên field name và code
//    @Override
//    public ClassDto save(ClassDto classDto) {
//       try
//       {
//           Class clazz = mapper.dtoToEntityClass(classDto);
//           //boolean result = repository.existsByNameOrCode(classDto.getName(), classDto.getCode()); && !result
//           if (!Objects.isNull(clazz) && validDate(classDto.getStartTime(),classDto.getEndTime())) {
//               Class returnEntity = repository.save(clazz);
//               return mapper.entityToDtoClass(returnEntity);
//           } else {
//               throw new BusinessException("201", "invalid param");
//           }
//       }catch (Exception e) {
//           throw new BusinessException("201", "invalid param");
//       }
//    }

    // cách 2: dùng query
    @Override
    public ClassDto save(ClassDto classDto) {
        Class clazz = mapper.dtoToEntityClass(classDto);
        Date currentDate = new Date();
        boolean result = repository.existsByNameOrCode(classDto.getName(), classDto.getCode());
        if (!Objects.isNull(clazz) &&
                classDto.getStartTime().before(currentDate) &&
                classDto.getEndTime().after(currentDate)) {
            Class returnEntity = repository.save(clazz);
            return mapper.entityToDtoClass(returnEntity);
        } else {
            throw new BusinessException("201", "invalid param");
        }
    }

    @Override
    public void delete(Long id){
        if (Objects.isNull(repository.findById(id))){
            throw  new IllegalArgumentException("classroom not found");
        }
        repository.deleteById(id);
    }
}
