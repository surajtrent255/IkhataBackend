package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.PaymentDTO;
import com.ishanitech.iaccountingrest.dto.PostDateCheckDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.exception.CustomSqlException;
import com.ishanitech.iaccountingrest.service.PaymentModeService;
import com.ishanitech.iaccountingrest.service.PaymentService;
import com.ishanitech.iaccountingrest.service.PostDateCheckService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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
            throw new CustomSqlException(e.getMessage());
        }

    }

    @GetMapping("/{SN}")
    public ResponseDTO<?> getPaymentDetailsById(@PathVariable("SN") int SN){

        try{
            return new ResponseDTO<>(paymentService.getPaymentDetailsById(SN));
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }

    }

    @PostMapping
    public ResponseDTO<?> addPaymentDetails(@RequestBody PaymentDTO paymentDTO){
        boolean PostCheck = paymentDTO.isPostDateCheck();
        Date payDate = paymentDTO.getPostCheckDate();
        try {
           long PaymentId = paymentService.addPaymentDetails(paymentDTO);
            if(PostCheck){
                postDateCheckService.addPostChequeInfo(PaymentId,payDate);
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
            throw new CustomSqlException(e.getMessage());
        }
    }

    @DeleteMapping("/{SN}")
    public void DeletePaymentDetails(@PathVariable("SN") int SN){

        try {
          paymentService.DeletePaymentDetails(SN);
          postDateCheckService.deletePostDateCheckInfo(SN);
        }catch (Exception e){
            log.error(e.getMessage());
            throw new CustomSqlException(e.getMessage());
        }
    }

    @PutMapping
    public ResponseDTO<?> updatePaymentDetails(@RequestBody PaymentDTO paymentDTO){
        boolean PostCheck = paymentDTO.isPostDateCheck();
        Date payDate = paymentDTO.getPostCheckDate();
        long  PaymentId = paymentDTO.getSN();
        List<PostDateCheckDTO> data = postDateCheckService.getAllPostChequeInfo(PaymentId);
        try {
            paymentService.updatePaymentDetails(paymentDTO);
            if(PostCheck){
                if(data.isEmpty()){
                    postDateCheckService.addPostChequeInfo(PaymentId,payDate);
                }else{
                    postDateCheckService.updatePostDateCheck(PaymentId,payDate);
                }
            }
            if(!PostCheck){
                if(!data.isEmpty()){
                    postDateCheckService.deletePostDateCheckInfo((int)PaymentId);
                }
            }
            return new ResponseDTO<>("Updated Successfully");
        }catch (Exception exception){
            log.error(exception.getMessage());
            throw new CustomSqlException(exception.getMessage());
        }
    }

}
