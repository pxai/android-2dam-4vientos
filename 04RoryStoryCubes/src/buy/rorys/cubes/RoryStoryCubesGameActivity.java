package buy.rorys.cubes;


import java.util.Random;
import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class RoryStoryCubesGameActivity extends Activity {
	TextView game_title;
	String mode;
	boolean confirm;
	static boolean yesOrNo = false;
	
	LinearLayout confirmLayout;
	ImageView dice1;
	ImageView dice2;
	ImageView dice3;
	ImageView dice4;
	ImageView dice5;
	ImageView dice6;
	ImageView dice7;
	ImageView dice8;
	ImageView dice9;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game);
        game_title = (TextView) this.findViewById(R.id.game_title);
        dice1 = (ImageView) this.findViewById(R.id.dice1);
        dice2 = (ImageView) this.findViewById(R.id.dice2);
        dice3 = (ImageView) this.findViewById(R.id.dice3);
        dice4 = (ImageView) this.findViewById(R.id.dice4);
        dice5 = (ImageView) this.findViewById(R.id.dice5);
        dice6 = (ImageView) this.findViewById(R.id.dice6);
        dice7 = (ImageView) this.findViewById(R.id.dice7);
        dice8 = (ImageView) this.findViewById(R.id.dice8);
        dice9 = (ImageView) this.findViewById(R.id.dice9);
        Button botonmenu = (Button) findViewById(R.id.botonmenu);
        confirmLayout = (LinearLayout) findViewById(R.id.confirmLayout);

        // Cargamos las preferences
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(RoryStoryCubesGameActivity.this);
        // El modo de tirada, si no está puesto cogerá real
        mode = sp.getString("roll_mode", "real");
        // Si hay que confirmar o no antes de tirar, por defecto no
        confirm = sp.getBoolean("confirm_roll", false);
        
        // Establecemos en la pantalla quién cuenta la historia,
        // sacado también de las preferences
        game_title.setText("Teller: " + sp.getString("story_teller","none")+ " mode:" + mode + " Confirm:" + confirm);

        // Al botón para volver al menú le asignamos un Listener
        // y en el onClick hacemos que se vuelva atrás.
        botonmenu.setOnClickListener(new View.OnClickListener() {
    	    public void onClick(View view) {
    	    	RoryStoryCubesGameActivity.this.finish();
    	    }
    	});
       
    }
    
    /**
     * roll
     * Método que lleva a cabo la tirada de dados
     * @param View v
     */
    public void roll (View v) {
    	// Si había que pedir confirmación, abrimos un dialogo
    	yesOrNo = false;
    	
    	if (confirm) {
    		// Mostramos el layout con los dos botones
    		confirmLayout.setVisibility(View.VISIBLE);
    	} else {
    		// si no hay confirmación, vamos al grano:
    		// 	Según el modo seleccionado
    		// en las preferencias lo hacemos de una forma o de otra.
    		if (mode.equals("full")) {
    			this.fullRoll();
    		} else { // Este es como el modo real con dados físicos
    			this.realRoll();
    		}
    	}
    }
    
    /**
     * rollconfirm
     * Si se pulsa el botón que confirma la tirada 
     */
    public void rollconfirm (View v) {
		if (mode.equals("full")) {
			this.fullRoll();
		} else { // Este es como el modo real con dados físicos
			this.realRoll();
		}
		// Y cerramos la layout con los botones
		confirmLayout.setVisibility(View.GONE);
    }

    /**
     * rollcancel
     * Si se pulsa el botón para cancelar la tirada
     */
    public void rollcancel (View v) {
		// Simplemente cerramos la layout con los botones
		confirmLayout.setVisibility(View.GONE);
    }
    

    /**
     * fullRoll
     * Lanza los dados combinando todas las caras
     */
    public void fullRoll () {

  		// En el modo full puede salir cualquier
		// imagen, incluso las del mismo dado
    	int dices[] = new int[]{R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5,R.drawable.a6,
    			R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4,R.drawable.b5,R.drawable.b6,    			
    			R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,R.drawable.c6,    			
    			R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,R.drawable.d6,    			
    			R.drawable.e1,R.drawable.e2,R.drawable.e3,R.drawable.e4,R.drawable.e5,R.drawable.e6,    			
    			R.drawable.f1,R.drawable.f2,R.drawable.f3,R.drawable.f4,R.drawable.f5,R.drawable.f6,    			
    			R.drawable.g1,R.drawable.g2,R.drawable.g3,R.drawable.g4,R.drawable.g5,R.drawable.g6,    			
    			R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.h4,R.drawable.h5,R.drawable.h6,    			
    			R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4,R.drawable.i5,R.drawable.i6    			
    			};

	    	// Barajamos las imágenes
	    	shuffle(dices);
	    	int i = 0;
	    	
	    	dice1.setImageResource(dices[i]); i++;
	    	dice2.setImageResource(dices[i]); i++;
	    	dice3.setImageResource(dices[i]); i++;
	    	dice4.setImageResource(dices[i]); i++;
	    	dice5.setImageResource(dices[i]); i++;
	    	dice6.setImageResource(dices[i]); i++;
	    	dice7.setImageResource(dices[i]); i++;
	    	dice8.setImageResource(dices[i]); i++;
	    	dice9.setImageResource(dices[i]);
    }
    
    /**
     * realRoll
     * Lanza los dados como se hace con los de verdad
     */
    public  void realRoll () {
    	Random random = new Random();
    	int dices1[] = new int[]{R.drawable.a1,R.drawable.a2,R.drawable.a3,R.drawable.a4,R.drawable.a5,R.drawable.a6};
    	int dices2[] = new int[]{R.drawable.b1,R.drawable.b2,R.drawable.b3,R.drawable.b4,R.drawable.b5,R.drawable.b6};
    	int dices3[] = new int[]{R.drawable.c1,R.drawable.c2,R.drawable.c3,R.drawable.c4,R.drawable.c5,R.drawable.c6};   			
    	int dices4[] = new int[]{R.drawable.d1,R.drawable.d2,R.drawable.d3,R.drawable.d4,R.drawable.d5,R.drawable.d6};   			
    	int dices5[] = new int[]{R.drawable.e1,R.drawable.e2,R.drawable.e3,R.drawable.e4,R.drawable.e5,R.drawable.e6};   			
    	int dices6[] = new int[]{R.drawable.f1,R.drawable.f2,R.drawable.f3,R.drawable.f4,R.drawable.f5,R.drawable.f6};   			
    	int dices7[] = new int[]{R.drawable.g1,R.drawable.g2,R.drawable.g3,R.drawable.g4,R.drawable.g5,R.drawable.g6};   			
    	int dices8[] = new int[]{R.drawable.h1,R.drawable.h2,R.drawable.h3,R.drawable.h4,R.drawable.h5,R.drawable.h6};   			
    	int dices9[] = new int[]{R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4,R.drawable.i5,R.drawable.i6};  			
    			
    	// Elegimos una cara aleatoria para cada uno y fuera
    	dice1.setImageResource(dices1[random.nextInt(6)]); 
    	dice2.setImageResource(dices2[random.nextInt(6)]); 
    	dice3.setImageResource(dices3[random.nextInt(6)]); 
    	dice4.setImageResource(dices4[random.nextInt(6)]); 
    	dice5.setImageResource(dices5[random.nextInt(6)]); 
    	dice6.setImageResource(dices6[random.nextInt(6)]); 
    	dice7.setImageResource(dices7[random.nextInt(6)]); 
    	dice8.setImageResource(dices8[random.nextInt(6)]); 
    	dice9.setImageResource(dices9[random.nextInt(6)]);
    }
    
  
   
    /**
     * shuffle
     * Método para barajar un array, basado en un algoritmo
     * cuyo nombre no recuerdo ahora mismo. Gran homenaje el mío.
     * @param values
     */
    public void shuffle (int values[]) {
    	int len = values.length;
    	int i = len, j = 0, tmp = 0;
    	Random r = new Random();
    	
    	 while (i-- > 0) {
    	 	j = r.nextInt(len);
    		tmp = values[i];
    		values[i] = values[j];
    		values[j] = tmp;
     	}
    	
    	//return values;
    }
}