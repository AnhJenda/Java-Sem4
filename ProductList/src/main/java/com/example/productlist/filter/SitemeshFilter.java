package com.example.productlist.filter;
/*Welcome to my show !

@author: NgKhanh
Date: 6/9/2023
Time: 8:50 PM

ProjectName: reflection*/

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

public class SitemeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder){
        builder.addDecoratorPath("/*", "/common/admin.jsp").addExcludedPath("/login");
    }
}
