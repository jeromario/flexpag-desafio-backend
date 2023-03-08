package com.flexpag.paymentscheduler.service;

import com.flexpag.paymentscheduler.exception.BusinessException;
import com.flexpag.paymentscheduler.model.User;
import com.flexpag.paymentscheduler.model.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public List<User> buscarTodos() {
        return repository.findAll();
    }

   public Optional<User> buscarPorId(Long id) {
        return repository.findById(id);
    }

   public void salvar(User user) {

       Optional<User> byCpf = repository.findByCpf(user.getCpf());
       if (byCpf.isPresent()){
           if (!byCpf.get().getId().equals(user.getId())){
               throw new BusinessException("CPF já cadastrado!");
           }
       }else{repository.save(user);}

    }

    public void atualizar(Long id, User user) {
        Optional<User> optionalUser = this.buscarPorId(id);
        if (optionalUser.isEmpty()){
            throw new BusinessException("Usuario ja é cadastrado!");
        }else {
            user.setId(id);
            salvar(user);
        }


    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
