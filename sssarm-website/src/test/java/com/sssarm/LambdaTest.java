package com.sssarm;

import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * @Author Eric
 * @Date 2017/5/11 20:23
 * @Desc
 */
public class LambdaTest {

    @org.junit.Test
    public void test() throws JsonProcessingException {
        /*List<Company> list = new ArrayList<>();
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

        System.out.println(JSONUtil.OBJECT_MAPPER.writeValueAsString(map));*/
    }

}
