package com.krglow.taskmanager.dataset.user;

import static com.krglow.taskmanager.dataset.SqlCommonInit.FORWARD_SLASH;


public interface SqlUserInit {

    String USER = FORWARD_SLASH + "users";

    String SAMPLE_ID = "id";
    String SAMPLE_NAME = "name";
    String SAMPLE_SURNAME = "surname";
    String SAMPLE_EMAIL = "email";

    String ITEM = "$.content[0].";

    String NAME_VALUE = "Anna";
    String ID_VALUE = "1";
    String SURNAME_VALUE = "Kowalska";
    String EMAIL_VALUE = "anna.kowalska@example.com";

}
