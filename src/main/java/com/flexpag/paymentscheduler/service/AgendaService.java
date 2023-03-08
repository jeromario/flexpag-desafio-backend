package com.flexpag.paymentscheduler.service;

import com.flexpag.paymentscheduler.exception.BusinessException;
import com.flexpag.paymentscheduler.model.Agenda;
import com.flexpag.paymentscheduler.model.AgendaRepository;
import com.flexpag.paymentscheduler.model.Status;
import com.flexpag.paymentscheduler.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class AgendaService {


    private final AgendaRepository repository;
    private final UserService userService;


    public List<Agenda> buscarTodos(){
        return repository.findAll();
    }
    public Optional<Agenda> buscarPorId(Long id){
        Optional<Agenda> byId = repository.findById(id);
        if (byId.isPresent()){
            return repository.findById(id);
        }else {
            throw new BusinessException("Id não encontrado!");
        }


    }
    public void salvar(Agenda agenda){

        Optional<User> optionalUser = userService.buscarPorId(agenda.getUser().getId());
        if (optionalUser.isEmpty()){
            throw new BusinessException("Usuario não encontrado!");
        }
        agenda.setUser(optionalUser.get());
        agenda.setDate(LocalDate.now());
        agenda.setStatus(Status.PENDING);
        repository.save(agenda);

    }
    public void pagarAgenda(Long id){
        Optional<Agenda> optionalAgenda = repository.findById(id);
        if (!optionalAgenda.isPresent()){
            throw new BusinessException("Agendamento não encontrado!");
        } else if (optionalAgenda.get().getStatus() == Status.PAID) {
            throw new BusinessException("Pagamento já foi realizado");
        }else {
            optionalAgenda.get().setStatus(Status.PAID);
            repository.save(optionalAgenda.get());
        }

    }
    public void atualizar(Long id, Agenda agenda){
        Optional<Agenda> optionalAgenda = this.buscarPorId(id);
        if (optionalAgenda.isEmpty()){
            throw new BusinessException("Agendamento não cadastrado!");
        } else if (optionalAgenda.get().getStatus() == Status.PAID) {
            throw new BusinessException("Pagemento já realizado!");
        } else{
            agenda.setId(id);
            salvar(agenda);
        }
    }

    public void deletar(Long id){
        Optional<Agenda> byId = repository.findById(id);
        if (byId.isPresent()){
            Agenda agenda = byId.get();
            if (agenda.getStatus() == Status.PENDING){
                repository.deleteById(id);
            }else {
                throw new BusinessException("Não deletado, pagamento já realizado!");

            }
        }else {
            throw new BusinessException("Agendamento não encontrado!");
        }

    }


}
