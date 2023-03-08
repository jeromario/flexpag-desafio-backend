package com.flexpag.paymentscheduler.api.controller;

import com.flexpag.paymentscheduler.model.Agenda;
import com.flexpag.paymentscheduler.service.AgendaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/agenda")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaService service;

    @GetMapping
    public ResponseEntity<List<Agenda>> buscarTodos(){
        return ResponseEntity.ok(service.buscarTodos());

    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Agenda>>buscarPorId(@PathVariable Long id){
        Optional<Agenda> optionalAgenda = service.buscarPorId(id);
        if (optionalAgenda.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.buscarPorId(id));
    }
    @PostMapping
    public ResponseEntity<Agenda>salvar(@RequestBody Agenda agenda){
        service.salvar(agenda);
        return ResponseEntity.ok(agenda);
    }
    public ResponseEntity<Agenda>pagarAgenda(@PathVariable Long id){
        Optional<Agenda> buscarPorId = service.buscarPorId(id);
        if (buscarPorId.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        service.pagarAgenda(id);
        return ResponseEntity.ok(buscarPorId.get());


    }
    @PutMapping("/{id}")
    public ResponseEntity<Agenda>atualizar(@PathVariable Long id, @RequestBody Agenda agenda){
        service.atualizar(id,agenda);
        return ResponseEntity.ok(agenda);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Agenda>deletar(@PathVariable Long id){
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
