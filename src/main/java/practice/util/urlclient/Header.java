package practice.util.urlclient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Header {
    private final String key;
    private final String value;
}
