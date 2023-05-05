package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.FeatureControlService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/v1/feature/control")
@AllArgsConstructor
public class FeatureControlController {

    private final FeatureControlService featureControlService;

    @GetMapping
    public ResponseDTO<?> getFeatureControls(){
        try {
            return new ResponseDTO<>(featureControlService.getFeatureControls());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }

    }


    @PostMapping
    public ResponseDTO<?> assignFeatureControlToUser(@RequestParam("featureId") int[] featureId,@RequestParam("userId") int userId,@RequestParam("companyId") int companyId){
try {
    featureControlService.assignFeatureControlToUser(featureId,userId,companyId);
    return new ResponseDTO<>("Assigned Feature Control Successfully");
}catch(Exception e){
    log.error(e.getMessage());
    throw new CustomSqlException(e.getMessage());
}
    }


    @GetMapping("/{companyId}")
    public ResponseDTO<?> getFeatureControlOfUsersForListing(@PathVariable("companyId") int companyId){
        try{
            return new ResponseDTO<>(featureControlService.getFeatureControlOfUsersForListing(companyId));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @PutMapping
    public ResponseDTO<?> enableDisableFeatureControlForUser(@RequestParam("userId") int userId,@RequestParam("status") boolean status,@RequestParam("featureId") int featureId){
        try {
            featureControlService.enableDisableFeatureControlForUser(userId,status,featureId);
            return new ResponseDTO<>("Feature update Successfully");
        }catch(Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/user")
    public ResponseDTO<?> getAllFeatureControlOfUserByUserId(@RequestParam("companyId") int companyId,@RequestParam("userId") int userId){
        try {
            return new ResponseDTO<>(featureControlService.getAllFeatureControlOfUserByUserId(companyId,userId));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/for/localStorage")
    public ResponseDTO<?> getFeatureControlDetailsForLocalStorage(@RequestParam("companyId") int companyId,@RequestParam("userId") int userId){
        try{
          return new ResponseDTO<>(featureControlService.getFeatureControlDetailsForLocalStorage(companyId,userId));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }
}
