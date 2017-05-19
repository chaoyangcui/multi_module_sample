package com.sssarm;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.sssarm.entity.Company;
import com.sssarm.entity.Util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author Eric
 * @Date 2017/5/11 20:23
 * @Desc
 */
public class Test {

    @org.junit.Test
    public void test() throws JsonProcessingException {
        List<Company> list = new ArrayList<>();
        list.add(Company.builder().address("china").build());
        list.sort(new Comparator<Company>() {
            @Override
            public int compare(Company o1, Company o2) {
                return 0;
            }
        });
        list.sort((o1, o2) -> o2.getAddress().compareTo(o1.getAddress()));
        list = list
                .stream()
                .filter(company -> company.getAddress().equalsIgnoreCase("china"))
                .collect(Collectors.toList());
        Map<String, Company> map =
                list
                .stream()
                .filter(company -> company.getAddress().equalsIgnoreCase("china"))
                .collect(Collectors.toMap(Company::getAddress, temp -> temp));

        System.out.println(Util.OBJECT_MAPPER.writeValueAsString(map));
    }

}
