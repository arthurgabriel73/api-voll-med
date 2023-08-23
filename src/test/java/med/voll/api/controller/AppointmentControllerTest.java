package med.voll.api.controller;

import org.junit.jupiter.api.DisplayName;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import med.voll.api.domain.appointment.AppointmentDetailData;
import med.voll.api.domain.appointment.AppointmentSchedule;
import med.voll.api.domain.appointment.RegisterAppointmentRecord;
import med.voll.api.domain.physician.Specialty;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AppointmentControllerTest {
  
  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private JacksonTester<RegisterAppointmentRecord> registerAppoitmentRecordJson;

  @Autowired
  private JacksonTester<AppointmentDetailData> appointmentDetailDataJson;

  @MockBean
  private AppointmentSchedule appointmentSchedule;

  @Test
  @DisplayName("should return http 400 when invalid param is given")
  @WithMockUser
  void testDoScheduleScenario1() throws Exception {
    var response = mockMvc.perform(MockMvcRequestBuilders.post("/appointments"))
    .andReturn().getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());

  }

  @Test
  @DisplayName("should return http 200 when valid params are given")
  @WithMockUser
  void testDoScheduleScenario2() throws Exception {
    var date = LocalDateTime.now().plusHours(1);
    var specialty = Specialty.CARDIOLOGY;
    var appointmentDetails = new AppointmentDetailData(null, 2l, 5l, date);
    when(appointmentSchedule.doSchedule(any())).thenReturn(appointmentDetails);
    var expectedJson = appointmentDetailDataJson.write(
      appointmentDetails
    ).getJson(); 
    var response = mockMvc.perform(MockMvcRequestBuilders.post("/appointments")
    .contentType(MediaType.APPLICATION_JSON)
    .content(registerAppoitmentRecordJson.write(
        new RegisterAppointmentRecord(2L, 5L, date, specialty)
      ).getJson())
    )
    .andReturn().getResponse();

    assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    assertThat(response.getContentAsString()).isEqualTo(expectedJson);

  }
}
