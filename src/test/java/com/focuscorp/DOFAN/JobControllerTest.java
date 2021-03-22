package com.focuscorp.DOFAN;

import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.verify;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import com.focuscorp.DOFAN.model.Credential;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest
public class JobControllerTest {
    @Autowired
    private MockMvc mockMvc;

    //ALL_CREDENTIALS
    @Test
    public void AllCredentials_ShouldReturnCredentials() throws Exception   {
        this.mockMvc.perform(MockMvcRequestBuilders.get("/credentials"))
                .andDo(print())
                .andExpect(view().name("/jobs/credentials"))
                .andExpect(status().isOk());
    }

    //DELETE_CREDENTIAL

    @Test
    public void deleteCredential_ShouldDeleteCredential() throws Exception   {

     /*String id ="id1234";
      this.mockMvc.perform(MockMvcRequestBuilders.get("/delete/"+id)
                   .param("id", id))
                   .andDo(print())
                   .andExpect(MockMvcResultMatchers.redirectedUrl("/users"))
                  . andExpect(status().isFound());
                // .andExpect(view().name("/users"))
               //  .andExpect(status().isOk());

             verify(userService, times(1)).deleteById(id);*/
    }
    @Test
    public void AddCredential_ShouldAddCredential() throws Exception   {

        Credential oCredential = new Credential();
        mockMvc.perform(MockMvcRequestBuilders.post("/addCredential")
                .flashAttr("newCredential", oCredential)).andDo(print())
                //  .param("username", sUsername)
                //  .param("email", sEmail)
                //  .param("password", sPassword))

                .andExpect(MockMvcResultMatchers.redirectedUrl("/credentials"))
                . andExpect(status().isFound());
    }

    @Test
    public void EditCredential_ShouldEditCredential() throws Exception   {

    }
}
