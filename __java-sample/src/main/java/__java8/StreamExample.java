package __java8;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/28 18:16
 * Desc    Setting | Editor | File and Code Templates
 */
public class StreamExample {

    public static void main(String[] args) {
        String[] openids = {
                "ofLyRjp4GGXArtQ_pbGuabkUz89w","ofLyRjhrXiabZ2su9GsXWtWeIMhk","ofLyRjuCAeM67wDE7tSlEAABf3VA","ofLyRju41CWLyxNTRHMABka4jaXU","ofLyRjv-ZSpleySL-6Ab4688DM0Y","ofLyRjq_54Zq2ZySAbmhfTd_6fM8","ofLyRjsCpNABv8EeZjBKYAIx3m8g","ofLyRjgsx80y7asnHwABSVzId8PQ","ofLyRjmB-HAbnRduwUy2YiiN6bCY","ofLyRjpwfUArohabazOdCyFGErUk","ofLyRjtukmISMIuxqS0d3AbE61-4",
                "ofLyRjnqJigPKhlJp9EkvQOabF8w","ofLyRjlMfwmCvabHdklXA9LC7OEI","ofLyRjve8jTQh_H4Abbv6R_TbPJ4","ofLyRjlQSDMIlxLxrZyncab8SKKU","ofLyRjndLf3DrabSKMjrGqXJ1BFg","ofLyRjqAbBsGx1hTtWhgvnFmGeho","ofLyRjhBdwGbVWNLAbmU_FtvWOKI","ofLyRjq6It6DGASZx14mU-qWaB-4","ofLyRjqQRrvW2cPFl4NaBIB5xDgA","ofLyRjtRTw9taBSKllsLUBRQ4PXY","ofLyRjkSg6MabIrDTKn-A-WjEWi4",
                "ofLyRjv1aV7mxi38jGe_LQg3abF0","ofLyRjnpGPTXaB9v6Pty1C7OF6xM","ofLyRjkXc2XnkUABMX-J0uzI_bXU","ofLyRji9BDLAbvwT3wtC29Bbhgfs","ofLyRji7bPAbb_hdzNaymT6HAXAs","ofLyRjmkc0-UIC2KCpyic4vjWAbU","ofLyRjmmLEc4yOzameABki22n2VI","ofLyRjv9w2KrfrObdHOb2hQf3Ab8","ofLyRjjq7c5YAmfw0bJlO5zabAx4","ofLyRjrIWlABxapYq-xwlIyHgkH0","ofLyRjvzqJTaB1zaw5avc9JPAD4k",
                "ofLyRjtw7ZAbXbV6p1qSWQrn9VTM","ofLyRjkf8V_wVM7T_aB9sTOr5ybY","ofLyRjlDauKzj-aBfBymuoxfqBjU","ofLyRjpVN4RL_TWINoR3xaBoSpC8","ofLyRjjjqnxwecdJiMfU3OAb58CA","ofLyRjtFo641rOAB3W_tO7DLfQIQ","ofLyRjlTdABUtyRwo_cG9ltYF9gQ","ofLyRjswO1brNtwbktuI7dYjAbE4","ofLyRjodqpcFD-ZLoZabTNKuFtig","ofLyRjpAB0jORwEhYlSh0_9-zfWQ","ofLyRjoLKhYYMq8GRplU4RFABJ20",
                "ofLyRjj8QvabyJuSjEjBdktJsx1w","ofLyRjmiLxW9PeeVnqp3aABvnVbM","ofLyRjgYKEabDjmAEkI2HMW5-8yA","ofLyRjlDtnmRC-y1DFhABl8dk-Q4","ofLyRjsKoeFj6ji6O88IELztaBnY","ofLyRjhFlvvC7IsRBvc9jSaAbbzU","ofLyRjo7AbwkzqCfNkADsK28RzPU","ofLyRjihR_i3NnruPN9cHBPhaB5E","ofLyRjukj-p6Fw6q7Gab412Fcjew","ofLyRjmDdOJAbamWR_G4eBwS72Hs","ofLyRjraboaKjXWkhT5l6vf0tFtM",
                "ofLyRjjbSD4M-LMkRhoxdzIkaBeM","ofLyRjgk2oiAbVbFZjWC0wGzhPK4","ofLyRjs7LM7MIPGewTqnBzJAbumY","ofLyRjuZcWeFKRDmAbNDTDM8sgQc","ofLyRjl6wZVp2JJABrZV1O2A8HCU",
                "ofLyRjj9RwqXaaBm1Oc7otQmSHgs","ofLyRjhdpuoGEI-GquyY7i41wAbM","ofLyRjt-ulEGDIcFGabyQajzuzqI","ofLyRjlV8U1irH-G2glcs93ab1CA","ofLyRjhhxWHh-u3GtpABuK231H8g",
                "ofLyRjuBAB-Wynt1PEyAL-I7ZQaM","ofLyRjuFTn6cA2DViABNdIyDo3as","ofLyRjou-8WBrrK2abO7CxCjUvt4","ofLyRjjjqnxwecdJiMfU3OAb58CA","ofLyRjlTdABUtyRwo_cG9ltYF9gQ",
                "ofLyRjmabWK4w157sAPBBeQ6QgDs","ofLyRjrhmZ_HtHnEClp0Ghiab0A4","ofLyRjs6Msp7tBDqgKHhabhmnWgs","ofLyRjtJgFHrabsYzrOANnYmqRZ8","ofLyRjuSabTOhF3MddBU1hsxR3lo",
                "ofLyRjgaonSukMNkcS8rIoIaBP74","ofLyRjh9rYABwhaDddK5HUCGEfjA","ofLyRjiDOABxJ3uqYDYMiTwPUDjo","ofLyRjtYzyvHpVY0HyjnaBsTIAIk","ofLyRjpDFngABBoYNnRc27I7uuPQ",
                "ofLyRjpABqk6f6rFp9132ZQh4ei0","ofLyRjlOXQqFpLHAAbsEv6TKrz28","ofLyRjljGnv5LsNnabRYC7yw_6kc","ofLyRjsN2aab3MhHIfD_UpAuM2Kk","ofLyRjiZT9NPaBnbowuwGS_YDst8",
                "ofLyRjkZNBVlQAK-zESABEPu5vwo","ofLyRjtEoODivBveZ094itb7LaBs","ofLyRjjuxClJkNaM7ROCUh8g0AB8","ofLyRjsAb2K5W2askNMwIvpnnVN4","ofLyRjm6TiEt3E8c-1nKaB1QKfCM",
                "ofLyRjm8XahStDtFGkiAbdKXZOQ4","ofLyRjopaf8ONnABFM_XVs9NWXBU","ofLyRjqECeju22OPkbHHhABaSKbY","ofLyRjiVlqIb-8abmg4A7xx_HT4Y","ofLyRjl9dhMkxLTrkg3Hk_ABvDNA",
                "ofLyRjhlkvRTgX9_u3u8pYGfAB2s","ofLyRjtJgFHrabsYzrOANnYmqRZ8","ofLyRjpcdvP5_LgQLAblNiitiW9M","ofLyRjgxax37QmEablDe511m4goY","ofLyRjgNuNJVX_urzabHpTtinO5s"
        };

        final Map<String, Integer> count = new HashMap<>();
        Stream.of(openids).forEach((e) -> {
            Integer cnt;
            count.put(e, (cnt = count.get(e)) != null ? cnt + 1 : 1);
        });

        Stream<String> stream = Stream.of(count.keySet().toArray(new String[0]));
        List<String> keys =
                stream.filter((key) -> count.get(key) > 1)
                        .collect(Collectors.toList());
        System.out.println(keys);

        List<String> list =
                Stream.of(count.entrySet().toArray(new Map.Entry[0]))
                        .filter((e) -> (Integer) e.getValue() > 1)
                        .map((e) -> (String) e.getKey())
                        .collect(Collectors.toList());
        System.out.println(list);

    }

}
