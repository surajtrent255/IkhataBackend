package com.ishanitech.iaccountingrest.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoryProductDTO {
    private int id;
    private String name;
    private String description;
    private int parentId;
    private int userId;
    private int companyId;
    private Date created_date;
    private Date edited_date;
    private List<CategoryProductDTO> childCategories;

    public CategoryProductDTO(int id, String name, int parentId) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.childCategories = new ArrayList<>();
    }

    public void addChildCategory(CategoryProductDTO childCategory) {
        if(!this.childCategories.contains(childCategory)) {
            this.childCategories.add(childCategory);
        }
    }
}
