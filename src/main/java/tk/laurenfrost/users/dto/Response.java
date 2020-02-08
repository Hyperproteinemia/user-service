package tk.laurenfrost.users.dto;

import lombok.Data;

@Data
public class Response {
    private boolean successful;
    private Object data;
}