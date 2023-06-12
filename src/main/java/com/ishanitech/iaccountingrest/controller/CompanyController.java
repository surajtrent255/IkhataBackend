package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.CompanyDTO;
import com.ishanitech.iaccountingrest.dto.CompanyLogoDTO;
import com.ishanitech.iaccountingrest.dto.CompanyWithUserIdDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.CompanyService;
import com.ishanitech.iaccountingrest.service.UserConfigurationService;
import com.ishanitech.iaccountingrest.utils.FileUtilService;
import com.ishanitech.iaccountingrest.utils.HostDetailsUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {


    private final CompanyService companyService;

    private final UserConfigurationService userConfigurationService;

    private final FileUtilService fileUtilService;

    @Autowired
    private HostDetailsUtil resolveHostAddress;



    @PostMapping("/add")
    public ResponseDTO<?> addCompany(@RequestBody CompanyWithUserIdDTO companyWithUserIdDTO) {
        int userId = companyWithUserIdDTO.getUserId();
        CompanyDTO companyDTO = companyWithUserIdDTO.getCompanyDTO();

        try {
         Integer   result = companyService.addCompany(companyDTO,userId);
           CompanyDTO companyDTO1 = (CompanyDTO) companyService.getCompanyByPanNo(companyDTO.getPanNo());
            userConfigurationService.addUserRole(userId,companyDTO1.getCompanyId(),new int[] {1});
            return new ResponseDTO<>( result);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new CustomSqlException("Something went wrong while adding Company!");
        }

    }

    @GetMapping
    public ResponseDTO<List<CompanyDTO>> getAllCompanyList(){
        try{
            return new ResponseDTO<List<CompanyDTO>>(companyService.getAllCompanyList());
        }catch(Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException("SomeThing Went Wrong");
        }

    }

    @GetMapping("/getById")
    public ResponseDTO<?> getCompanyByIdForEdit(@RequestParam("companyId") int companyId){
        try{
            return new ResponseDTO<>(companyService.getCompanyByIdForEdit(companyId));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/userCompany/{userId}")
    public ResponseDTO<?> getCompanyByUserId(@PathVariable("userId") int userId){
        try{
            return new ResponseDTO<>(companyService.getCompanyByUserId(userId));
        }catch(Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException("SomeThing Went Wrong");
        }

    }

    @DeleteMapping("/{id}")
    public void deleteCompany(@PathVariable("id") int id){
        try{
            companyService.deleteCompany(id);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }


    @GetMapping("/searchBy")
    public ResponseDTO<List<CompanyDTO>> fetchCustomer(@RequestParam("searchMethod") int searchMethod, @RequestParam("customerPhoneOrPan") long customerPhoneOrPan){
        try{
            return new ResponseDTO<List<CompanyDTO>>(companyService.getCustomerInfosByPanOrPhone(searchMethod, customerPhoneOrPan));
        } catch(Exception ex){
            log.error("something went wrong while fetching customer infos " + ex.getMessage());
            throw new CustomSqlException("something went wrong while fetching customerInfos" );
        }
    }

    @PostMapping("/logo/upload")
    public ResponseDTO<?> uploadLogo(@RequestParam("file") MultipartFile file,@RequestParam("companyId") int companyId){
        try {
            fileUtilService.storeFile(file);
            CompanyLogoDTO companyLogoDTO = new CompanyLogoDTO();
            companyLogoDTO.setCompanyId(companyId);
            companyLogoDTO.setImageName(file.getOriginalFilename());
            companyService.addCompanyLogo(companyLogoDTO);
            return new ResponseDTO<>("Logo successfully uploaded: " + file.getOriginalFilename());
        } catch (Exception e) {
           log.error(e.getMessage());
           throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/logo")
    public ResponseDTO<?> getCompanyLogo(@RequestParam("companyId") int companyId){
        try{
           CompanyLogoDTO companyLogoDTO = companyService.getCompanyLogo(companyId);
            String url = resolveHostAddress.getHostUrl()+"images/"+companyLogoDTO.getImageName();
            companyLogoDTO.setImageUrl(url);
            return new ResponseDTO<>(companyLogoDTO);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @PutMapping("/edit")
    public ResponseDTO<String> editCompany(@RequestBody CompanyDTO companyDTO){
        try{
            companyService.editCompany(companyDTO);
            return new ResponseDTO<>("Successfully Edited");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }


}
