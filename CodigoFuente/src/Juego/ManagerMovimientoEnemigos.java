package Juego;

import Enemigo.Enemigo;
import Enemigo.Lakitu;
import Enemigo.Spiny;
import Plataforma.Plataforma;
import Visitor.ManagerColisionesEnemigo;

public class ManagerMovimientoEnemigos extends Thread {

    protected Juego juego;
    ManagerColisionesEnemigo managerColisionesEnemigo;
    private volatile boolean running;
    private long lastSpinyTime = 0;
    private static final long SPINY_INTERVAL = 20000;
    private static final int FPS = 60;
    private static final long FRAME_TIME = 1000 / FPS;

    public ManagerMovimientoEnemigos(Juego juego) {
        this.juego = juego;
//        managerColisionesEnemigo = new ManagerColisionesEnemigo(juego.getNivelActual().getEnemigos());
    }

    @Override
    public void run() {
        running = true;
        lastSpinyTime = System.currentTimeMillis();

        while (running) {
            long startTime = System.currentTimeMillis();

            juego.detectarColisionesEnemigosYManejar();
            for (Enemigo enemigo : juego.getNivelActual().getEnemigos()) {
                enemigo.actualizarPosicion();
            }

            long currentMillis = System.currentTimeMillis();
            if (currentMillis - lastSpinyTime >= SPINY_INTERVAL && currentMillis - lastSpinyTime <= SPINY_INTERVAL + 1000) {
                for (Lakitu lakitu : juego.getNivelActual().getLakitus()) {
                    if (lakitu.tieneSpinys()) {
                        Spiny spiny = lakitu.arrojarSpiny();
                        juego.registrarObserverEntidad(spiny);
                        juego.getNivelActual().agregarEnemigo(spiny);
                        lakitu.restarSpiny();
                    }
                }
                lastSpinyTime = currentMillis;
            }

            long timeTaken = System.currentTimeMillis() - startTime;
            long sleepTime = FRAME_TIME - timeTaken;
            if (sleepTime > 0) {
                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    @Override
    public void interrupt() {
        running = false;
        super.interrupt();
    }
}
