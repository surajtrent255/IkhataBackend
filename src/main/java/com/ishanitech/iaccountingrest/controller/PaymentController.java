package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.PaymentDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.PaymentModeService;
import com.ishanitech.iaccountingrest.service.PaymentService;
import com.ishanitech.iaccountingrest.service.PostDateCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    private final PaymentModeService paymentModeService;

    private final PostDateCheckService postDateCheckService;

    @GetMapping
    public ResponseDTO<?> getPaymentDetailsByCompanyId(@RequestParam("companyId") int companyId){

        try{
            return new ResponseDTO<>(paymentService.getPaymentDetailsByCompanyId(companyId));
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>();
    }

    @GetMapping("/{SN}")
    public ResponseDTO<?> getPaymentDetailsById(@PathVariable("SN") int SN){

        try{
            return new ResponseDTO<>(paymentService.getPaymentDetailsById(SN));
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>();
    }

    @PostMapping
    public ResponseDTO<?> addPaymentDetails(@RequestBody PaymentDTO paymentDTO){
        boolean PostCheck = paymentDTO.isPostDateCheck();
        Long PaymentId;
        try {
            PaymentId = paymentService.addPaymentDetails(paymentDTO);
            if(PostCheck){
                long checkNo = paymentDTO.getCheckNo();
                Date payDate = paymentDTO.getPostCheckDate();
                postDateCheckService.addPostChequeInfo(checkNo,PaymentId,payDate);
            }
            return new ResponseDTO<>("Added Successfully");
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }

    }

    @GetMapping("/mode")
    public ResponseDTO<?> getAllModeDetails(){
        try {
            return new ResponseDTO<>(paymentModeService.getAllPaymentModeDetails());
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>();
    }

    @DeleteMapping("/{SN}")
    public void DeletePaymentDetails(@PathVariable("SN") int SN){

        try {
          paymentService.DeletePaymentDetails(SN);
          postDateCheckService.deletePostDateCheckInfo(SN);
        }catch (Exception e){
            log.error(e.getMessage());
        }
    }

}
