package dummies;

import org.springframework.beans.factory.annotation.Required;

import java.util.ArrayList;
import java.util.List;

public class DataRepository {

    private List<DummyData> dummies = new ArrayList<>();

    public DataRepository(){}

    @Required
    public void setDummies(List<DummyData> dummies) { this.dummies = dummies; }
    public List<DummyData> getDummies() { return this.dummies; }

    public void instantiateDataMock(){
        this.dummies.forEach(DummyData::instantiateDataMock);
    }
}
