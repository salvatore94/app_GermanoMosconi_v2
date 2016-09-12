package org.polito.salvatore.testgiugno;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ListViewCompat;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

   // Button b1 = findViewById(R.id.button1);

    final String[] titoliPath = new String[] { "ah_non_lo_so_io", "avanti_e_n_drio", "avv_bisagno", "carte_co_la_cola", "chi_e_quel_mona", "chi_fa_quel_rumore_li",
            "come_se_ciama_elo_li","cos_e_caduto", "cosa_ghe_qua_sotto", "d_p", "dai_va_la", "dio_bono_de_dio", "dio_bubu",
            "dio_camaja_de_dio", "dio_cazzo", "dio_pa_pa_pa_pa", "dio_po_dio", "dio_porco__dio_cane", "dio_ss", "e_con_questo",
            "gabriele_sborina", "germano_e_il_telefono", "il_punteggio_dio_cane", "in_primo_piano", "la_societa", "ma_che_ooooh",
            "ma_e_possibile_che_sia_cosi_degli_imbecilli", "madonna_puttinaaaa", "madonna", "no_nessuno", "no_no_vai_in_mona", "non_e_possibile",
            "non_si_puo_scrivere_ste_notizie_in_maiuscolo", "orco_dio_in_serie", "passar_davanti", "pilota_romano_romano_andrea_de_cesaris", "porca_madonna",
            "porco_dio_1", "porco_dio_2", "portata_la_madonna", "se_non_bestemmio_guarda", "se_trovo_quello_che_mi_fa_innervosire",
            "se_venite_avanti_vi_do_un_pugno", "serie_esplosiva", "serrare_la_porta", "squadre", "stronzi", "tutto_da_capo",
            "vaffanculo_ti_e_tutti_quanti", "vaffanculo", "vai_in_mona", "vedo_tutto_meno_quello_che_dovrei_vedere"};


    final String[] titoli = new String[]  { "Ah non lo so io", "Avanti e n drio", "Avv bisagno", "Carte co la cola", "Chi e quel mona", "Chi fa quel rumore li",
            "Come se ciama elo li","Cos\'e caduto", "Cosa ghe qua sotto", "Dio porco", "Dai va la", "Dio bono de Dio", "Dio bubu",
            "Dio camaja de Dio", "Dio cazzo", "Dio pa pa pa pa", "Dio po Dio", "Dio porco  Dio cane", "Dio ss", "E con questo",
            "Gabriele Sborina", "Germano e il telefono", "Il punteggio Dio cane", "In primo piano", "La societa", "Ma che ooooh",
            "Ma e possibile che sia cosi degli imbecilli", "Madonna puttinaaaa", "Madonna", "No nessuno", "No no vai in mona", "Non e possibile",
            "Non si puo scrivere ste notizie in maiuscolo", "Orco Dio in serie", "Passar davanti", "Pilota romano romano Andrea DeCesaris", "Porca Madonna",
            "Porco Dio 1", "Porco Dio 2", "Portata la madonna", "Se non bestemmio guarda", "Se trovo quello che mi fa innervosire",
            "Se venite avanti vi do un pugno", "Serie esplosiva", "Serrare la porta", "Squadre", "Stronzi", "Tutto da capo",
            "Vaffanculo ti e tutti quanti", "Vaffanculo", "Vai in mona", "Vedo tutto meno quello che dovrei vedere" };


    MediaPlayer mp = new MediaPlayer();

    public void stopAndReset (MediaPlayer m) {
        m.stop();
        m.reset();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ArrayAdapter<String> mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1,  titoli);

        //final Animation testAnimation = AnimationUtils.loadAnimation(this, R.anim.scale);

        ListView v1 = (ListView) findViewById(R.id.view1);
        v1.setVisibility(View.INVISIBLE);
        v1.setAdapter(mAdapter);
        ;

        v1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(mp.isPlaying())
                {
                    stopAndReset(mp);
                }


                AssetFileDescriptor media = null;
                try {
                    media = getAssets().openFd(titoliPath[position] + ".mp3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    mp.setDataSource(media.getFileDescriptor(), media.getStartOffset(), media.getLength());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mp.selectTrack(position);
                try {
                    mp.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }


                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        stopAndReset(mp);
                    }
                });


                mp.start();


                Log.d("List", "Ho cliccato sull'elemento con titolo " + position);
            }
        });

        Animation testAnimation3 = AnimationUtils.loadAnimation(this, R.anim.entrata);
        Button b = (Button) findViewById(R.id.button1);
        b.startAnimation(testAnimation3);

    }

    public void clicca(View view) {

        if(mp.isPlaying()) {
            stopAndReset(mp);
        }

        View v1 = findViewById(R.id.view1);
        final Animation testAnimation1 = AnimationUtils.loadAnimation(this, R.anim.scale);
        final Animation testAnimation2 = AnimationUtils.loadAnimation(this, R.anim.reverse);
        if(v1.getVisibility()==View.INVISIBLE) {

            v1.setVisibility(View.VISIBLE);
            v1.startAnimation(testAnimation1);

        }
        else {
            v1.setVisibility(View.INVISIBLE);
            v1.startAnimation(testAnimation2);
        }
    }
}
