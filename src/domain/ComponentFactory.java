
package domain;

public class ComponentFactory {
	private Rectangle component;

	public Rectangle getComponent(String type) {
		switch(type) {
		case "atom":
			component = new UnshieldedAtom(KUvidGame.getShooter().getPositionX(), KUvidGame.getShooter().getPositionY(), 40, 40);
			break;
		case "molecule":
			component = new Molecule((int) (Math.random() * (KUvidGame.WIDTH * 0.5) + KUvidGame.L), 20, 0, 0);
			break;
		case "fallingPowerup":
			component =  new FallingPowerup ((int) (Math.random() * (KUvidGame.WIDTH * 0.5) + KUvidGame.L), 20, 0, 0); 
			break;
		case "shootablePowerup":
			component = new ShootablePowerup((int) (Math.random() * (KUvidGame.WIDTH * 0.5) + KUvidGame.L), 20, 0, 0, KUvidGame.getShootablePowerupType());
			break;
		case "blocker":
		 	component = new ReactionBlocker((int) (Math.random() * (KUvidGame.WIDTH * 0.5) + KUvidGame.L), 20, 0, 0);
			break;

		}
		return component;
	}
}

