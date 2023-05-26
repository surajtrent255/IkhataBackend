package com.ishanitech.iaccountingrest.dto;
import com.ishanitech.iaccountingrest.utils.PaginationTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaginationTypeDTO {
    private Integer prev ;
    private Integer current ;
    private Integer next ;
    private Integer currentFirstObjectId;
    private Integer currentLastObjectId ;
    private Integer productsLimit ;
    private PaginationTypeEnum type;
}
