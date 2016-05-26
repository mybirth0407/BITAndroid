package bit.timestable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import bit.timestable.config.Config;
import bit.timestable.prime.PrimeArray;

import java.util.*;

public class GameActivity extends AppCompatActivity
                          implements View.OnClickListener {
    private Random random = new Random();
    private Timer timer = new Timer();
    private Integer[] question = new Integer[2];
    private Integer answer;
    private Integer countQuestions = 0;
    private Integer countRight = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        findViewById(R.id.button_back).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    timer.cancel();
                    finish();
                }
            });
        timer.schedule(new RemainTimeCount(), 1000, 1000);

        init();
    }

    private void init() {
        ((TextView) findViewById(
            R.id.tv_remain_time_count)).setText("" + Config.TIME);
        ((TextView) findViewById(R.id.tv_answer_count)).setText(
            "" + countRight + "/" + countQuestions);

        answer = newQuestion();
    }

    /* 정답을 반환 */
    public Integer newQuestion() {
        question[0] = random.nextInt(8) + 1;
        question[1] = random.nextInt(8) + 1;

        ((TextView) findViewById(
            R.id.tv_first_num)).setText("" + question[0]);
        ((TextView) findViewById(
            R.id.tv_second_num)).setText("" + question[1]);

        Integer[] buttonsId = {-1,
                               R.id.button1, R.id.button2, R.id.button3,
                               R.id.button4, R.id.button5, R.id.button6,
                               R.id.button7, R.id.button8, R.id.button9};
        Button[] buttons = new Button[Config.MAX_TIMES + 1];
        List<Integer> list = PrimeArray.sieve();
        Set<Integer> set = new HashSet<>();

        for (int i = 1; i <= Config.MAX_TIMES; i++) {
            buttons[i] = (Button) findViewById(buttonsId[i]);
            Integer n = list.get(random.nextInt(list.size()));
            while (true) {
                if (set.contains(n) == true) {
                    n = list.get(random.nextInt(list.size()));
                }
                else {
                    set.add(n);
                    break;
                }
            }
            buttons[i].setText("" + n);
            buttons[i].setOnClickListener(this);
        }

        Integer a = question[0] * question[1];
        buttons[random.nextInt(8) + 1].setText("" + a);

        return a;
    }

    @Override
    public void onClick(View v) {
        // Button 타입이 아닐 수 없지만, 변경 될 수 있으니깐...
        if (v instanceof Button == false) {
            return;
        }
        String textButton = ((Button) v).getText().toString();
        // 0 또는 양의 정수가 아니면...
        if (textButton.matches("\\d+") == false) {
            return;
        }

        int userAnswer = Integer.parseInt(textButton);
        if (userAnswer == answer) {
            countRight++;
        }
        countQuestions++;
        ((TextView) findViewById(R.id.tv_answer_count)).setText(
            "" + countRight + "/" + countQuestions);
        answer = newQuestion();
    }

    public class RemainTimeCount extends TimerTask {
        private Integer seconds = Config.TIME;

        @Override
        public void run() {
            if (seconds-- < 1) {
                timer.cancel();
                finish();
                return;
            }

            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    TextView tvRemainTimeCount = (TextView) findViewById(
                        R.id.tv_remain_time_count);
                    tvRemainTimeCount.setText("" + seconds);
                }
            });
        }
    }
}
