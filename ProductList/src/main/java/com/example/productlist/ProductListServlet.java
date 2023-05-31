package com.example.productlist;

import com.example.productlist.Repositories.MySQLProductRepository;
import com.example.productlist.Repositories.ProductReporitory;
import com.example.productlist.Services.DefaultProductService;
import com.example.productlist.Services.ProductService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/*
    @author: Dinh Quang Anh
    Date   : 5/31/2023
    Project: ProductList
*/
@WebServlet("/productList")
public class ProductListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        ProductReporitory productRepository = new MySQLProductRepository(); // Sử dụng implementation của ProductRepository cho MySQL
        productService = new DefaultProductService(productRepository);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int pageNumber = 1;
        int pageSize = 10;

        String pageParam = request.getParameter("page");
        if (pageParam != null) {
            pageNumber = Integer.parseInt(pageParam);
        }

        String searchName = request.getParameter("searchName");

        List<Product> products = productService.getProducts(pageNumber, pageSize, searchName);
        int totalPages = productService.getTotalPages(pageSize, searchName);

        request.setAttribute("products", products);
        request.setAttribute("totalPages", totalPages);

        request.getRequestDispatcher("product_list.jsp").forward(request, response);
    }
}
