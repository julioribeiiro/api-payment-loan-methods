package com.jcrm.dnio.project2.jcrm_dnio_project_2.paymentTests;

import com.jcrm.dnio.project2.jcrm_dnio_project_2.loan.LoanController;
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
public class LoanControllerTest extends JcrmDnioProject2ApplicationTest {

    private MockMvc mockMvc;

    @Autowired
    private LoanController loanController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(loanController).build();
    }

    @Test
    public void POSTTestLoanController() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/loan")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"cnpj\": \"09507468000141\", \"loanDate\": \"2022-10-19\"}")
                ).andExpect(MockMvcResultMatchers.status().is2xxSuccessful())
                .andExpect(jsonPath("$.cnpj").value("09507468000141"));
    }

    @Test(expected = NestedServletException.class)
    public void POSTTestLoanControllerErrorCnpjLength() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/loan")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"cnpj\": \"0950746800014\", \"loanDate\": \"2022-10-19\"}")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test(expected = NestedServletException.class)
    public void POSTTestLoanControllerErrorDate() throws Exception {
        this.mockMvc.perform(MockMvcRequestBuilders.post("http://localhost:8080/api/v1/loan")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"cnpj\": \"09507468000141\", \"date\": \"2020-10-16\" }")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test(expected = AssertionError.class)
    public void PUTTestLoanControllerErrorCnpj() throws Exception {
        Integer id = 8;
        this.mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/api/v1/loan/edit" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"cnpj\": \"0950746800041\", \"date\": \"2022-10-19\" }")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test(expected = AssertionError.class)
    public void PUTTestLoanControllerErrorDate() throws Exception {
        Integer id = 8;
        this.mockMvc.perform(MockMvcRequestBuilders.put("http://localhost:8080/api/v1/loan/edit" + id)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"cnpj\": \"09507468000141\", \"date\": \"2020-10-15\" }")
        ).andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    public void DELETETestLoanController() throws Exception {
        Integer id = 2;
        this.mockMvc.perform(MockMvcRequestBuilders.delete("http://localhost:8080/api/v1/loan/" + id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
    }
}
