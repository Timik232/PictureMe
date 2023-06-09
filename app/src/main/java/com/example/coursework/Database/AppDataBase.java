package com.example.coursework.Database;

import static com.example.coursework.PictureMe.getAppContext;

import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.coursework.R;
import java.util.Random;

@Database(entities = {DataChatPerson.class, PortfolioPerson.class, Message.class}, version = 10)
public abstract class AppDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "PictureMe";
    private static AppDataBase instance;

    public static synchronized AppDataBase getDatabase() {
        if (instance == null) {
            instance = Room.databaseBuilder(
                            getAppContext(),
                            AppDataBase.class,
                            DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .addCallback(chatCallback)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback chatCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            Log.d("DOCallback", "Wait");
            new DbAsyncTask(instance).execute();

        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            Log.d("DOCallback", "Open");
            new DbAsyncTask(instance).execute();
        }
    };
    private static class DbAsyncTask extends AsyncTask<Void, Void, Void> {
        private String generateRandomTime() {
            Random random = new Random();
            int hour = random.nextInt(24);
            int minute = random.nextInt(60);
            String time = String.format("%02d:%02d", hour, minute);
            return time;
        }
        public static String generateRandomRate(double min, double max) {
            Random random = new Random();
            double number = min + random.nextDouble() * (max - min);
            number = Math.round(number * 10) / 10.0;
            return Double.toString(number);
        }
        public static String generateRandomPrice() {
            Random random = new Random();
            int price = random.nextInt(4901) + 100;
            String priceString = price + "₽";
            return priceString;
        }

        private chatDAO chatDao;
        private portfolioDAO portfolioDao;
        private messageDAO messageDao;
        String[] names = new String[11];
        int[] photos;
        String[] portfolios;
        String [] last_messages;
        String[] times;
        int[] icons;

        private DbAsyncTask(AppDataBase db) {
            chatDao = db.chatDao();
            portfolioDao = db.portfolioDao();
            messageDao = db.messageDao();
            names[0] = "Кирилл Евдокимов";
            names[1] = "Олег Вохрин";
            names[2] = "Валера Чечня";
            names[3] = "Бретт Оутри";
            names[4] = "Дарья Аникова";
            names[5] = "Денис Бойко";
            names[6] = "Роман Фомичев";
            names[7] = "Маша Соломаха";
            names[8] = "Анастасия Татуйко";
            names[9] = "Жак Фреско";
            names[10] = "Данил Петров";
            photos = new int[]{
                    R.drawable.ava,
                    R.drawable.oleg,
                    R.drawable.valera,
                    R.drawable.stahli,
                    R.drawable.dasha,
                    R.drawable.denis,
                    R.drawable.roman,
                    R.drawable.masha,
                    R.drawable.nastya,
                    R.drawable.ladno,
                    R.drawable.danil
            };
            portfolios =  new String[]{
                    "Я фотограф-любитель, который старается запечатлеть красоту мира в своих работах. Я не считаю себя профессионалом, но моя страсть к фотографии и желание научиться показывать красоту  через объектив делают меня счастливым",
                    "Мои работы - воплощение искусства и красоты. Я - фотограф, чьи фотографии наполнены страстью и роскошью. Мои портреты и пейзажи - это произведения искусства, которые оставят вас восхищёнными и заставят забыть о невзгодах",
                    "Ок, чуваки, вы знаете, я начинающий фотограф, и у меня есть своё портфолио! Я сфоткал кучу крутых фоток, и теперь хочу показать всем, на что я способен.",
                    "Моя работа - воплощать идеи в красивые фотографии, которые останутся с вами на всю жизнь. Я готов воплотить в жизнь любую вашу идею! Моя работа не заканчивается на моменте съемки. Я уделяю большое внимание обработке и редактированию фотографий, чтобы каждое изображение было настоящим произведением искусства. Если вы ищете профессионального фотографа то вы по адресу,",
                    "Добро пожаловать в мое портфолио фотографа! Я представляю коллекцию работ, которые охватывают разнообразные темы и жанры. Мои фотографии нацелены на захват важных моментов, эмоции и истории, которые могут возникнуть в любой сфере жизни. Я стремлюсь передать красоту простых моментов, архитектуры, природы и портретов, исследуя различные техники и композиции. Моя цель - создать визуальные повествования, которые вдохновляют зрителя и призывают к размышлениям. Приглашаю вас ознакомиться с моим портфолио и поделиться вашими впечатлениями. Спасибо!"
            };
            last_messages = new String[]{
                    "Спасибо большое!!!",
                    "Здравствуйте!",
                    "Ну как там с деньгами?",
                    "Классно получилось",
                    "Рада помочь)",
                    "Эта особенно понравилась",
                    "Поставлю на аватарку",
                    "Красота",
                    "Харош",
                    "Ладно точка",
                    "Я, конечно, всё понимаю..."
            };
            icons = new int[] {
                    R.drawable.enhanced,
                    R.drawable.basic,
                    R.drawable.auto
            };
            times = new String[11];
            for (int i = 0; i < 11; i++){
                times[i] =  generateRandomTime();
            }
        }
        @Override
        protected Void doInBackground(Void... voids) {
            if (chatDao.getChat("Кирилл Евдокимов") == null) {
                Log.d("DOCallback", "do you do");
                for (int i = 0; i < 11; i++){
                    chatDao.saveChat(new DataChatPerson(names[i], photos[i], last_messages[i], times[i], "Комолов Тимур"));
                }
                for (int i = 0; i < 11; i++){
                    messageDao.saveMessage(new Message(names[i], chatDao.getChat(names[i]).getId(),
                            last_messages[i], generateRandomTime(),
                            times[i]
                    ));
                }
            }
            if (portfolioDao.getPortfolio("Кирилл Евдокимов") == null){
                Random random = new Random();
                for (int i = 0; i < 11; i++){
                    portfolioDao.savePortfolio(new PortfolioPerson(names[i], photos[i],
                            icons[random.nextInt(3)],portfolios[random.nextInt(5)],
                            generateRandomRate(3.0, 5.0), generateRandomPrice()));
                }
                PortfolioPerson buf = portfolioDao.getPortfolio("Валера Чечня");
                buf.setCost("100₽");
                buf.setRate("1.0");
                buf.setText("Эй, я Валера Чечня, и вот мое портфолио, если интересует. Фотографировать - это такое дело, которое мне нравится, понимаешь? Я просто делаю фотки без особой задумки, ну типа быдловатого стиля, знаешь? Люблю снимать своих ребят на заднем дворе с пивком в руках или вообще на дискотеках, где все врываются и кайфуют. У меня нет никаких этих фанси-шманси идей, просто пытаюсь поймать атмосферу наших вечеринок и 'отрывов'. Не стремлюсь к искусству или чему-то такому, просто кайфую от съемки таких моментов, которые реально запоминаются. Так что, если хочешь окунуться в нашу быдло-жизнь, давай глянем мое портфолио и может быть, найдешь что-то интересное. Пошли!");
                buf.setIcon(R.drawable.auto);
                portfolioDao.updatePortfolio(buf);
                buf = portfolioDao.getPortfolio("Маша Соломаха");
                buf.setText("Привет, я Маша, фотографирую на плёнку, кайфую от старых школьных технологий. Моё портфолио — это целый коктейль эмоций и прилив ностальгии. Я ловлю моменты, которые легко бы проскочили мимо, когда время не жалко. Моя цель — создавать фотки, которые рассказывают свою историю. Я вылавливаю взаимодействие света и тени, чтобы запечатлеть те эфемерные моменты, которые будут жить вечно на пленке. Я играю с черно-белыми оттенками и яркими красками, чтобы увлечь зрителя в путешествие в прошлое, показать, как я вижу мир.");
                buf.setIcon(R.drawable.basic);
                portfolioDao.updatePortfolio(buf);
                buf = portfolioDao.getPortfolio("Дарья Аникова");
                buf.setText("Привет! Я Даша, начинающий фотограф, но с огромным оптимизмом и энтузиазмом! Я верю, что каждое фото — это возможность запечатлеть мир таким, каким я его вижу: полным красок, эмоций и историй. Хоть я только начинаю свой путь, я готова погрузиться в мир фотографии и открыть новые горизонты. Мои работы отражают мою страсть к исследованию, а каждая фотография — шаг вперед к самовыражению и творческому росту. Я рада делиться своими уловленными мгновениями с другими людьми и вдохновлять их своим оптимизмом. Давайте вместе запечатлим мир во всей его красе и уникальности!");
                portfolioDao.updatePortfolio(buf);
                buf = portfolioDao.getPortfolio("Роман Фомичев");
                buf.setText("Здравствуйте! Меня зовут Роман, и я представляю своё портфолио в качестве фотографа. Обладая техническими навыками и профессиональным оборудованием, я создаю композиционно сбалансированные и технически правильные фотографии. Мое портфолио охватывает различные жанры, включая портреты, пейзажи и архитектуру. Я стремлюсь к безупречности в использовании света, композиции и обработке изображений, чтобы достичь высокого качества фотографий. Моя цель — удовлетворить потребности клиентов, предоставляя им профессиональные фотографии, отвечающие их требованиям. С радостью предоставлю вам свои услуги и поделюсь моим портфолио для вашего рассмотрения. Спасибо за внимание.");
                portfolioDao.updatePortfolio(buf);
                buf = portfolioDao.getPortfolio("Анастасия Татуйко");
                buf.setText("Привет! Меня зовут Настя, мне 23 года, и я с удовольствием делюсь своим фотографическим творчеством с миром. Однажды я сняла портрет одного человека, и он настолько полюбил это фото, что решил поделиться им в соцсетях. Это стало для меня настоящим поворотным моментом, и я решила, что могу быть настоящим фотографом. В моем портфолио вы найдете ощущение взаимосвязи и искренности. Я стараюсь запечатлеть естественность момента, захватить эмоции и позволить каждому человеку быть самим собой на моих фотографиях. Через мои работы я пытаюсь показать красоту простых моментов, которые иначе могли бы быть упущены. Добро пожаловать в мир моей фотографии!");
                portfolioDao.updatePortfolio(buf);
                buf = portfolioDao.getPortfolio("Жак Фреско");
                buf.setText("Я фотографирую. На размышление даётся 30 секунд. Жак Фреско.");
                buf.setIcon(R.drawable.enhanced);
                buf.setRate("5.0");
                buf.setCost("9999₽");
                portfolioDao.updatePortfolio(buf);
            }
            Log.d("DOCallback", "Created");
            return null;
        }
    }
    public abstract chatDAO chatDao();
    public abstract portfolioDAO portfolioDao();
    public abstract messageDAO messageDao();
}
