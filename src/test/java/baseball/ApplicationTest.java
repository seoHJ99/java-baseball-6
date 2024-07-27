package baseball;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest  {

    @Test
    void replace() {
        String actual = "abc".replace("b", "d");
        assertThat(actual).isEqualTo("adc");
    }

    @Test
    void split(){
        String str1 = "1,2";
        String[] arr1 = str1.split(",");
        assertThat(arr1).containsExactly("1", "2");
    }

    @Test
    void substring(){
        String str1 = "(1,2)";
        String answer = str1.substring(1, str1.length()-1);
        assertThat(answer).isEqualTo("1,2");
    }

    @Test
    void charAt(){
        String str1 = "abc";
        assertThatThrownBy(()->{
            str1.charAt(5);
        }).isInstanceOf(StringIndexOutOfBoundsException.class);
    }
}
