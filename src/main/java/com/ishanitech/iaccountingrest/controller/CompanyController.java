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
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/panNo")
    public ResponseDTO<?> getCompanyByPan(@RequestParam("panNo") Long panNo){
        try {
            return new ResponseDTO<>(companyService.getCompanyByPanNo(panNo));

        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @PutMapping("/edit/companyLogo")
    public ResponseDTO<String> EditCompanyLogo(@RequestParam("file") MultipartFile file,@RequestParam("companyId") int companyId){
        try {
            CompanyLogoDTO companyLogoDTO = companyService.getCompanyLogo(companyId);
            if (companyLogoDTO != null){
                companyService.editCompanyLogo(file.getOriginalFilename(),companyId);

                boolean fileExist;
                fileExist = fileUtilService.checkIfFileExists(file.getOriginalFilename());
                if (fileExist){
                    fileUtilService.deleteFile(file.getOriginalFilename());
                }
                fileUtilService.storeFile(file);

            }
            if(null == companyLogoDTO){
                CompanyLogoDTO companyLogoDTO1 = new CompanyLogoDTO();
                companyLogoDTO1.setCompanyId(companyId);
                companyLogoDTO1.setImageName(file.getOriginalFilename());
                companyService.addCompanyLogo(companyLogoDTO1);
                boolean fileExist;
                fileExist = fileUtilService.checkIfFileExists(file.getOriginalFilename());
                if (fileExist){
                    fileUtilService.deleteFile(file.getOriginalFilename());
                }
                fileUtilService.storeFile(file);
            }
            return new ResponseDTO<>("Logo Successfully Changed");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

        /*
    controller for total Amount (today , this month, this year)
    @param date
     */

    @GetMapping("/date/today")
    public ResponseDTO<Integer> getTodayTotalSalesBillAmount(@RequestParam("todayDate")String todayDate,
                                                            @RequestParam("companyId") int companyId)
                                                            {
        try {
            return new ResponseDTO<>(companyService.customerAddedToday(todayDate,companyId));
        }
        catch (Exception e){
            e.printStackTrace();
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/date/month")
    public ResponseDTO<Integer> getThisMonthTotalSalesBillAmount(@RequestParam("month")String month,
                                                                @RequestParam("companyId") int companyId)
                                                               {
        try {
            return new ResponseDTO<>(companyService.customerAddedInMonth(month,companyId));
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/date/fiscalYear")
    public ResponseDTO<Integer> getThisFiscalYearTotalSalesBillAmount(@RequestParam("fiscalYear")String fiscalYear,
                                                                     @RequestParam("companyId") int companyId)
                                                                    {
        try {
            return new ResponseDTO<>(companyService.customerAddedThisYear(fiscalYear,companyId));
        }
        catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @GetMapping("/customers")
    public ResponseDTO<List<CompanyDTO>> getAllCustomersOfSpecificCompanyAndBranch(HttpServletRequest request){
        try{
            return new ResponseDTO<>(companyService.getAllCustomersByCompAndBranchId(request));
        } catch (Exception ex){
            log.error(ex.getMessage());
            throw new CustomSqlException("something went wrong while fetching customer");
        }
    }
}
