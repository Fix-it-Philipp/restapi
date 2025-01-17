package afp.restapi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import afp.restapi.models.Thread;
import afp.restapi.repositories.ThreadRepo;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ThreadService {

    private final ThreadRepo THREADREPO;
    
    public ThreadService(ThreadRepo THREADREPO){
        this.THREADREPO = THREADREPO;
    }

    public List<Thread> getThreads(){
        return THREADREPO.findAll();
    }

    public Optional<Thread> getThread(Long id){
        return THREADREPO.findById(id);
    }

    public Thread saveThread(Thread Thread){
        return THREADREPO.save(Thread);
    }

    public List<Thread> saveThreads(List<Thread> Threads){
        return THREADREPO.saveAll(Threads);
    }

    public Thread updateThread(Thread Thread){
        return THREADREPO.save(Thread);
    }

    public void deleteThread(Long id){
        THREADREPO.deleteById(id);
    }
}
