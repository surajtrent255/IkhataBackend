package com.ishanitech.iaccountingrest.controller;

import com.ishanitech.iaccountingrest.dto.PaymentDTO;
import com.ishanitech.iaccountingrest.dto.ResponseDTO;
import com.ishanitech.iaccountingrest.service.PaymentModeService;
import com.ishanitech.iaccountingrest.service.PaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("api/v1/payment")
public class PaymentController {

    private final PaymentService paymentService;

    private final PaymentModeService paymentModeService;

    @GetMapping
    public ResponseDTO<?> getPaymentDetailsByCompanyId(@RequestParam("companyId") int companyId){

        try{
            return new ResponseDTO<>(paymentService.getPaymentDetailsByCompanyId(companyId));
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>();
    }

    @PostMapping
    public ResponseDTO<?> addPaymentDetails(@RequestBody PaymentDTO paymentDTO){
        int success = 0;
        try {
            success = paymentService.addPaymentDetails(paymentDTO);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return new ResponseDTO<>("Data" + success);
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

}
