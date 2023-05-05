package com.ishanitech.iaccountingrest.controller;


import com.ishanitech.iaccountingrest.dto.FixedAssetsDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.service.FixedAssetsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/fixedAssets")
public class FixedAssetsController {

    private final FixedAssetsService fixedAssetsService;

    @GetMapping
    public ResponseDTO<?> getFixedAssetsDetails(@RequestParam("companyId") int companyId){
        try {
            return new ResponseDTO<>(fixedAssetsService.getFixedAssetsDetails(companyId));
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>("Done");
    }

    @GetMapping("/{SN}")
    public ResponseDTO<?> getFixedAssetsDetailsBySN(@PathVariable("SN") int SN){
        try {
            return new ResponseDTO<>(fixedAssetsService.getFixedAssetsBySN(SN));
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>("Done");
    }

    @PutMapping
    public ResponseDTO<?> updateFixedDetails(@RequestBody FixedAssetsDTO fixedAssetsDTO){
        try{
            fixedAssetsService.updateFixedAssets(fixedAssetsDTO);
        }catch (Exception e){
            log.error(e.getMessage());
        }

        return new ResponseDTO<>("Update Successfully");
    }

    @PostMapping
    public ResponseDTO<?> addFixedDetails(@RequestBody FixedAssetsDTO fixedAssetsDTO){
        try {
            return new ResponseDTO<>(fixedAssetsService.addFixedAssetsDetails(fixedAssetsDTO));
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>("Data Successfully Added");
    }

    @DeleteMapping("/{SN}")
    public ResponseDTO<?> deleteFixedAssets(@PathVariable("SN") int SN){
        try{
            fixedAssetsService.deleteFromAssets(SN);
        }catch(Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>("Successfully Deleted");
    }

}
