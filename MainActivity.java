package com.abstar.offlinegames;

import android.app.*;
import android.os.*;
import android.graphics.*;
import android.graphics.drawable.GradientDrawable;
import android.view.*;
import android.widget.*;
import java.util.*;

public class MainActivity extends Activity {
    LinearLayout root;
    int blue=Color.rgb(31,78,121);
    @Override public void onCreate(Bundle b){super.onCreate(b); home();}
    TextView title(String s,int size){
        TextView t=new TextView(this); t.setText(s); t.setTextSize(size); t.setTextColor(Color.WHITE);
        t.setGravity(Gravity.CENTER); t.setPadding(12,22,12,22); return t;
    }
    Button gameBtn(String text){
        Button b=new Button(this); b.setText(text); b.setTextSize(19); b.setAllCaps(false);
        b.setTextColor(Color.WHITE); b.setBackground(round(blue,28)); 
        LinearLayout.LayoutParams p=new LinearLayout.LayoutParams(-1,68); p.setMargins(24,8,24,8); b.setLayoutParams(p); return b;
    }
    GradientDrawable round(int c,int r){GradientDrawable g=new GradientDrawable();g.setColor(c);g.setCornerRadius(r);return g;}
    void base(String heading){
        root=new LinearLayout(this); root.setOrientation(LinearLayout.VERTICAL); root.setBackgroundColor(Color.rgb(245,247,250));
        TextView h=title(heading,25); h.setBackgroundColor(blue); root.addView(h);
        setContentView(root);
    }
    void home(){
        base("🎮 AB Star Offline Games");
        TextView sub=new TextView(this); sub.setText("Internet ki zaroorat nahi • Free play • Virtual coins only"); sub.setTextSize(16); sub.setGravity(17); sub.setPadding(8,18,8,18); root.addView(sub);
        Button l=gameBtn("🎲 Ludo"); root.addView(l); l.setOnClickListener(v->ludo());
        Button t=gameBtn("🃏 Teen Patti"); root.addView(t); t.setOnClickListener(v->teenPatti());
        Button p=gameBtn("🧩 Puzzle"); root.addView(p); p.setOnClickListener(v->puzzle());
        Button q=gameBtn("🫧 Bubble"); root.addView(q); q.setOnClickListener(v->bubble());
    }
    void back(){Button b=gameBtn("← Back to Home"); root.addView(b); b.setOnClickListener(v->home());}
    void ludo(){
        base("🎲 Ludo — Offline");
        TextView info=title("Players: 2–4\nRoll the dice and move your token!",20); info.setTextColor(Color.DKGRAY); root.addView(info);
        Button dice=gameBtn("🎲 ROLL DICE"); root.addView(dice);
        TextView result=new TextView(this); result.setTextSize(28); result.setGravity(17); result.setPadding(10,25,10,25); root.addView(result);
        Random r=new Random(); dice.setOnClickListener(v->result.setText("You rolled: "+(r.nextInt(6)+1)));
        back();
    }
    void teenPatti(){
        base("🃏 Teen Patti — Offline");
        TextView info=new TextView(this); info.setText("Virtual coins only\nNo real-money betting."); info.setTextSize(18); info.setGravity(17); info.setPadding(10,25,10,25); root.addView(info);
        Button deal=gameBtn("🃏 DEAL CARDS"); root.addView(deal);
        TextView cards=new TextView(this); cards.setTextSize(24); cards.setGravity(17); cards.setPadding(10,25,10,25); root.addView(cards);
        String[] suits={"♠","♥","♦","♣"}; String[] ranks={"A","2","3","4","5","6","7","8","9","10","J","Q","K"}; Random r=new Random();
        deal.setOnClickListener(v->{String s=""; for(int i=0;i<3;i++) s+=ranks[r.nextInt(13)]+suits[r.nextInt(4)]+"   "; cards.setText(s);});
        back();
    }
    void puzzle(){
        base("🧩 Puzzle — Number Challenge");
        TextView q=new TextView(this); q.setTextSize(22); q.setGravity(17); q.setPadding(10,25,10,25); root.addView(q);
        EditText ans=new EditText(this); ans.setHint("Answer"); ans.setInputType(2); root.addView(ans);
        Button check=gameBtn("CHECK"); root.addView(check);
        TextView msg=new TextView(this); msg.setTextSize(18); msg.setGravity(17); root.addView(msg);
        Random r=new Random(); int a=r.nextInt(20)+1,b=r.nextInt(20)+1; q.setText(a+" + "+b+" = ?");
        check.setOnClickListener(v->{try{msg.setText(Integer.parseInt(ans.getText().toString())==a+b?"✅ Correct!":"❌ Try again");}catch(Exception e){msg.setText("Answer likhein");}});
        back();
    }
    void bubble(){
        base("🫧 Bubble — Tap Challenge");
        TextView score=new TextView(this); score.setText("Score: 0"); score.setTextSize(25); score.setGravity(17); root.addView(score);
        Button tap=gameBtn("🫧 TAP BUBBLE"); root.addView(tap); final int[] n={0};
        tap.setOnClickListener(v->{n[0]++;score.setText("Score: "+n[0]);});
        back();
    }
}
