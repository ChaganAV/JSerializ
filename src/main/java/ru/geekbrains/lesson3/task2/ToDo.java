package ru.geekbrains.lesson3.task2;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class ToDo implements Externalizable {
    private String title;
    private boolean isDone;
    public ToDo(){

    }

    public ToDo(String title, boolean isDone) {
        this.title = title;
        this.isDone = isDone;
    }
    //region Externalizable implementaion
    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(title);
        out.writeObject(isDone);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        // важно сохранять порядок сериализации.
        title = (String) in.readObject();
        isDone = in.readBoolean();
    }
    //endregion

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
