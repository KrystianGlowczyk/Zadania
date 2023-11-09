package com.krglow.taskmanager.dataset.task;

import static com.krglow.taskmanager.dataset.SqlCommonInit.FORWARD_SLASH;


public interface SqlTaskInit {

    String TASK = FORWARD_SLASH + "tasks";
    String ID_2 = FORWARD_SLASH + "2";

    String SAMPLE_ID = "id";
    String SAMPLE_TITLE = "title";
    String SAMPLE_DESCRIPTION = "description";
    String SAMPLE_STATUSID = "statusId";
    String SAMPLE_STATUSNAME = "statusName";
    String SAMPLE_EXECUTETIME = "executeTime";
    String SAMPLE_ASSIGNEDUSERS = "assignedUsers";

    String ITEM = "$.content[0].";

    String TITLE_VALUE = "Zadanie 1";
    String ID_VALUE = "1";
    String DESCRIPTION_VALUE = "Opis zadania 1";
    String STATUSID_VALUE = "1";
    String STATUSNAME_VALUE = "Nowy";
    String ASSIGNEDUSERS_VALUE = "Anna Kowalska, Piotr Wiśniewski";
    String EXECUTETIME_VALUE = "2023-11-06T12:00:00";

}
