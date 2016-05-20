package in.omerjerk.processingdemo.sketch;

import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.ArrayList;

import in.omerjerk.processingdemo.MainActivity;
import processing.core.PApplet;
import processing.core.PImage;
import processing.core.PShape;
import processing.core.PVector;

/**
 * Created by root on 25/6/15.
 */
public class Particles extends PApplet {

    ParticleSystem ps;
    PImage sprite;
    float x=200;
    float y=50;
    float speed=10;
    float gravity=1;//toc do ball
    float z_1=12;
    float z_2=12;

/*    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.include_layout, container, false); //Contains empty RelativeLayout
        return view;

    }*/

 /*   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }*/

/*    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.include_layout, container,false);
    }*/


    private String url;

    @Override
    public void settings() {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int height = displaymetrics.heightPixels;
        int width = displaymetrics.widthPixels;
        Bundle extras = getActivity().getIntent().getExtras();
      //  url = extras.getString("url");
        x=width/2;
        y=height-200;
        size(width, height, P3D);
    }

    @Override
    public void setup() {
//        Toast.makeText(getActivity(), ""+MainActivity.url, Toast.LENGTH_SHORT).show();
        sprite = loadImage(MainActivity.url);
        ps = new ParticleSystem
                (2000);


        // Writing to the depth buffer is disabled to avoid rendering
        // artifacts due to the fact that the particles are semi-transparent
        // but not z-sorted.
        hint(DISABLE_DEPTH_MASK);
    }

    
    @Override
    public void draw() {
        //background(255);
        y=y-speed;
       // speed=speed-gravity;
        if(y>height){
       //     speed=speed* -1;
        }
        stroke(0);
        fill(175);
        ellipse(x,y,12,12);
        background(0);
        ps.update();
        ps.display();

        ps.setEmitter(mouseX,mouseY);

        fill(255);
        textSize(16);
//        Toast.makeText(getActivity(), "data", Toast.LENGTH_SHORT).show();
       // text("Frame rate: " + PApplet.parseInt(frameRate),10,20)
    }

    class Particle {

        PVector velocity;
        float lifespan = 255;

        PShape part;
        float partSize;

        PVector gravity = new PVector(0,0.1f);


        Particle() {
            partSize = random(10,60);
            part = createShape();
            part.beginShape(QUAD);
            part.noStroke();
            part.texture(sprite);
            part.normal(0, 0, 1);
            part.vertex(-partSize/2, -partSize/2, 0, 0);
            part.vertex(+partSize/2, -partSize/2, sprite.width, 0);
            part.vertex(+partSize/2, +partSize/2, sprite.width, sprite.height);
            part.vertex(-partSize/2, +partSize/2, 0, sprite.height);
            part.endShape();

            rebirth(width/2,height/2);
            lifespan = random(255);
        }

        PShape getShape() {
            return part;
        }

        void rebirth(float x, float y) {
            float a = random(TWO_PI);
            float speed = random(0.5f,4);
            velocity = new PVector(cos(a), sin(a));
            velocity.mult(speed);
            lifespan = 255;
            part.resetMatrix();
            part.translate(x, y);
        }

        boolean isDead() {
            if (lifespan < 0) {
                return true;
            } else {
                return false;
            }
        }


        public void update() {
            lifespan = lifespan - 1;
            velocity.add(gravity);

            part.setTint(color(255,lifespan));
            part.translate(velocity.x, velocity.y);
        }
    }

    class ParticleSystem {
        ArrayList<Particle> particles;

        PShape particleShape;

        ParticleSystem(int n) {
            particles = new ArrayList<Particle>();
            particleShape = createShape(PShape.GROUP);

            for (int i = 0; i < n; i++) {
                Particle p = new Particle();
                particles.add(p);
                particleShape.addChild(p.getShape());
            }
        }

        void update() {
            for (Particle p : particles) {
                p.update();
            }
        }
        void setEmitter(float x, float y) {
            for (Particle p : particles) {
                if (p.isDead()) {
                    p.rebirth(x, y);
                }
            }
        }

        void display() {

            shape(particleShape);
        }
    }
}
