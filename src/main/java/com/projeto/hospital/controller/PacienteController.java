package com.projeto.hospital.controller;

import com.projeto.hospital.dao.EspecialidadeDAO;
import com.projeto.hospital.dao.PacienteDAO;
import com.projeto.hospital.models.Especialidade;
import com.projeto.hospital.models.Paciente;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
@RequestMapping("/pacientes")
public class PacienteController {
    @RequestMapping(value = "/novoPaciente", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute("paciente", new Paciente());
        return "novoPaciente";
    }

    @RequestMapping(value = "/salvar", method = RequestMethod.POST)
    public String salvarMedico(@ModelAttribute Paciente paciente) throws SQLException {
        new PacienteDAO().insert(paciente);
        return "redirect:/pacientes";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public String listarEspecialidades(Model model) throws SQLException {
        model.addAttribute("pacientes", new PacienteDAO().findAll());
        return "pacientes";
    }
}
