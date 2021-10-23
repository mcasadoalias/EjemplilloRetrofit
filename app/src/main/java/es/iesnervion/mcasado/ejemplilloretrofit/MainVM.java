package es.iesnervion.mcasado.ejemplilloretrofit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

public class MainVM extends ViewModel {

    public LiveData<String> nameLiveData;
    private MutableLiveData<Integer> idLiveData;
    private Repo repo;

    public MainVM() {
        //TODO: It's better to inject this dependency
        this.repo = new Repo();
        this.idLiveData = new MutableLiveData<>(-1);
        this.nameLiveData = Transformations.switchMap(this.getIdLiveData(), id -> repo.getPersonName(id));
    }


    public MutableLiveData<Integer> getIdLiveData() {
        return idLiveData;
    }

    public void updateIdLiveData(int id) {
        this.idLiveData.setValue(id);
    }

    public LiveData<String> getNameLiveData() {
        return nameLiveData;
    }
}
