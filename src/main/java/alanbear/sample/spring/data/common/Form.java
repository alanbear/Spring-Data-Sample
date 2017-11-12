package alanbear.sample.spring.data.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Form{

    ONE("101","表單一"),
    TWO("102","表單二"),
    THREE("103","表單三");

    @Getter
    private String id;
    @Getter
    private String name;

    public static Form getWithId(final String id) {

        for (Form e : Form.values()) {
            if (e.id.equalsIgnoreCase(id)) {
                return e;
            }
        }
        return null;

    }

}
