package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.*;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.CompanyService;
import com.ishanitech.iaccountingrest.service.UserConfigurationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/api/v1/company")
@RequiredArgsConstructor
public class CompanyController {


    private final CompanyService companyService;

    private final UserConfigurationService userConfigurationService;


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

    @GetMapping("/{PanNo}")
    public ResponseDTO<?> getCompanyById(@PathVariable("PanNo") Long PanNo){
        try{
            return new ResponseDTO<>(companyService.getCompanyByPanNo(PanNo));
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

    @GetMapping("/image")
    public ResponseDTO<?> getCompanyLogo(@RequestParam("companyId") int companyId){
        try{
            return new ResponseDTO<>(companyService.getCompanyLogo(companyId)) ;
        }catch(Exception ioException){
            log.error(ioException.getMessage());
            throw new CustomSqlException(ioException.getMessage());
        }
    }

//    @PostMapping("/image/upload")
//    public ResponseDTO<?> addCompanyLogo(@RequestParam("file")MultipartFile file,@RequestParam("companyId") int companyId) throws IOException{
//                  try{
//                    companyService.addCompanyLogo(file,companyId);
//                    return new ResponseDTO<>("Logo Successfully Added");
//                }catch (IOException e){
//                    log.error(e.getMessage());
//                    throw new IOException(e.getMessage());
//                }
//    }

}
