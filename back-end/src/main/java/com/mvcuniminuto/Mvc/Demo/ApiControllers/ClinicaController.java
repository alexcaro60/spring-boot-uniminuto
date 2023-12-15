package com.mvcuniminuto.Mvc.Demo.ApiControllers;

import com.mvcuniminuto.Mvc.Demo.Entities.Cita;
import com.mvcuniminuto.Mvc.Demo.Entities.Medico;
import com.mvcuniminuto.Mvc.Demo.Models.EditMedico;
import com.mvcuniminuto.Mvc.Demo.Models.InsertCita;
import com.mvcuniminuto.Mvc.Demo.Models.InsertMedico;
import com.mvcuniminuto.Mvc.Demo.Repositories.CitaRepository;
import com.mvcuniminuto.Mvc.Demo.Repositories.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/Clinica")
@CrossOrigin(origins = "*", maxAge = 3600)
public class ClinicaController {

    private final MedicoRepository medicoRepository;
    private final CitaRepository citaRepository;

    @Autowired
    public ClinicaController(MedicoRepository medicoRepository, CitaRepository citaRepository) {
        this.medicoRepository = medicoRepository;
        this.citaRepository = citaRepository;
    }



    @GetMapping("/ObtenerMedicos")
    public List<Medico> obtenerMedicos() {
        return medicoRepository.findAll();
    }

    @PostMapping("/ObtenerDataMedico")
    public ResponseEntity<Object> obtenerDataMedico(@RequestBody int id) {
        try {
            Medico medico = medicoRepository.findById(id);

            if (medico != null) {
                return ResponseEntity.ok(medico);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/insertarMedico")
    public ResponseEntity<Object> insertarMedico(@RequestBody InsertMedico insertMedico) {
        try {
            Medico medico = new Medico();
            medico.setNombre(insertMedico.getNombre());

            Medico medicoGuardado = medicoRepository.save(medico);

            var successObject = Map.of(
                    "mensaje", "Insertado Correctamente",
                    "medicoInsertado", medicoGuardado
            );
            return new ResponseEntity<>(successObject, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/EditarMedico")
    public ResponseEntity<Object> editarMedico(@RequestBody EditMedico editMedico) {
        try {

            if (medicoRepository.existsById(editMedico.getId())) {

                Medico medico = medicoRepository.findById(editMedico.getId());
                medico.setNombre(editMedico.getNombre());
                medico.setEspecialidad(editMedico.getEspecialidad());

                Medico medicoActualizado = medicoRepository.save(medico);

                var successObject = Map.of(
                        "Mensaje", "Editado Correctamente",
                        "MedicoActualizado", medicoActualizado
                );

                return ResponseEntity.ok(successObject);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/ObtenerCitas")
    public List<Cita> obtenerCitas() {
        return citaRepository.findAll();
    }


    @PostMapping("/InsertarCitas")
    public ResponseEntity<Object> insertarCitas(@RequestBody InsertCita insertCita) {
        try {
            Optional<Medico> medicoOptional = Optional.ofNullable(medicoRepository.findById(insertCita.getIdMedico()));

            if (medicoOptional.isPresent()) {
                Medico medico = medicoOptional.get();
                Cita cita = new Cita();
                cita.setFecha(insertCita.getFecha());
                cita.setMedico(medico);
                Cita citaGuardada = citaRepository.save(cita);

                var successObject = Map.of(
                        "Mensaje", "Insertado Correctamente",
                        "CitaInsertada", citaGuardada
                );

                return new ResponseEntity<>(successObject, HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Médico no encontrado", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
