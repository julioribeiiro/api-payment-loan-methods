package com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentTests;

import com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentMethod.PaymentMethodController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.util.NestedServletException;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PaymentControllerTest extends JcrmDnioProject2ApplicationTest {
    private MockMvc mockMvc;

    @Autowired
    private PaymentMethodController paymentMethodControllerTest;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(paymentMethodControllerTest).build();
    }

    @Test
    public void POSTTestPaymentController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/payment_method")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"code\": \"912123\", \"description\": \"testando 23\", \"interestRate\": 0.5, \"tax\": 0.6}")
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.code").value("912123"))
                .andExpect(jsonPath("$.description").value("testando 23"))
                .andExpect(jsonPath("$.interestRate").value(0.5))
                .andExpect(jsonPath("$.tax").value(0.6));
    }

    @Test(expected = NestedServletException.class)
    public void POSTTestPaymentControllerErrorCodeLength() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/payment_method")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"91234\", \"description\": \"testando 123\", \"interestRate\": 0.5, \"tax\": 0.6}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test(expected = NestedServletException.class)
    public void POSTTestPaymentControllerErrorCodeField() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/payment_method")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"description\": \"testando 123\", \"interestRate\": 0.3, \"tax\": 0.7}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test(expected = NestedServletException.class)
    public void POSTTestPaymentControllerErrorDescriptionLength() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/payment_method")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"912122\", \"description\": \"testando 123 testando 123 testando 123 testando 123 testando 123 testando 123testando123testando 123testando 123testando 123testando123testando 123testando123testando 123 \", \"interestRate\": 0.1, \"tax\": 0.3}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test(expected = NestedServletException.class)
    public void POSTTestPaymentControllerErrorDescriptionField() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/payment_method")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"912122\", \"interestRate\": 0.4, \"tax\": 0.6}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test(expected = NestedServletException.class)
    public void POSTTestPaymentControllerErrorDescriptionBlank() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/payment_method")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"912122\", \"description\": \"\", \"interestRate\": 0.5, \"tax\": 0.6}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void POSTTestPaymentControllerErrorInterestRateDecimal() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/payment_method")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"912122\", \"description\": \"testando 123\", \"tax\": 0.5, \"interestRate\": -1}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test(expected = NestedServletException.class)
    public void POSTTestPaymentControllerErrorInterestRateField() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/payment_method")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"912122\", \"description\": \"testando 123\", \"tax\": 0.5}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void POSTTestPaymentControllerErrorTaxDecimal() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/payment_method")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"912122\", \"description\": \"testando 123\", \"tax\": 10, \"interestRate\": 0.5}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test(expected = NestedServletException.class)
    public void POSTTestPaymentControllerErrorTaxField() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/payment_method")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"code\": \"912122\", \"description\": \"testando 123\", \"interestRate\": 0.5}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }


    @Test
    public void PUTTestPaymentController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/api/v1/payment_method/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"id\": 10, \"code\": \"123123\", \"description\": \"testando 123\", \"interestRate\": 0.5, \"tax\": 0.5}")
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }

    @Test(expected = NestedServletException.class)
    public void PUTPaymentControllerErrorDescription() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/api/v1/payment_method/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 10, \"code\": \"123123\", \"description\": \"testando 123 123 123sdasdq eqw 09j00 00q9w jqw nwq h9q0w jdq09wd jqw90jd90 qw0djqw09 djqw90 jdqw 9djqw90djw09q jd09qwj d9q0wjd0 asdad\", \"interestRate\": 0.5, \"tax\": 0.5}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void PUTPaymentControllerErrorTax() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/api/v1/payment_method/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 10, \"code\": \"123123\", \"description\": \"testando 123\", \"interestRate\": 0.5, \"tax\": 15}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void PUTPaymentControllerErrorInterestRate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/api/v1/payment_method/edit")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"id\": 10, \"code\": \"123123\", \"description\": \"testando 123\", \"interestRate\": -1, \"tax\": 0.5}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void DELETEPaymentController() throws Exception {
        Integer id = 2;
        this.mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/api/v1/payment_method/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
