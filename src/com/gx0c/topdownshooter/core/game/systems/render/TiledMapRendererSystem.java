package com.gx0c.topdownshooter.core.game.systems.render;

import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.MapObject;
import com.badlogic.gdx.maps.MapProperties;
import com.badlogic.gdx.maps.objects.PolygonMapObject;
import com.badlogic.gdx.maps.objects.RectangleMapObject;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Disposable;
import com.gx0c.topdownshooter.core.Game;
import java.util.ArrayList;

public class TiledMapRendererSystem extends EntitySystem implements Disposable {
	private OrthographicCamera camera;

	private TiledMap map;
	private OrthogonalTiledMapRenderer mapRenderer;

	private int tileWidth, tileHeight;
	private int mapWidth, mapHeight;
	private float transformedMapWidth, transformedMapHeight;

	public TiledMapRendererSystem(OrthographicCamera camera) {
		this.camera = camera;

		map = new TmxMapLoader().load("maps/map.tmx");
		mapRenderer = new OrthogonalTiledMapRenderer(map, 1 / Game.PPM);

		MapProperties properties = map.getProperties();
		tileWidth = properties.get("tilewidth", Integer.class);
		tileHeight = properties.get("tileheight", Integer.class);
		mapWidth = tileWidth * properties.get("width", Integer.class);
		mapHeight = tileHeight * properties.get("height", Integer.class);
		transformedMapWidth = mapWidth / Game.PPM;
		transformedMapHeight = mapHeight / Game.PPM;
	}

	@Override
	public void update(float deltaTime) {
		mapRenderer.setView(camera);
		mapRenderer.render();
	}

	public void getCollidable(World world) {
		for (MapObject object : map.getLayers().get("collidable").getObjects()) {
			float[] vertices = ((PolygonMapObject) object).getPolygon().getTransformedVertices();
			float[] worldVertices = new float[vertices.length];
			for (int i = 0; i < vertices.length; i++)
				worldVertices[i] = vertices[i] / Game.PPM;

			PolygonShape shape = new PolygonShape();
			shape.set(worldVertices);

			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyDef.BodyType.StaticBody;

			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = shape;
			fixtureDef.density = 1000f;
			fixtureDef.filter.categoryBits = Game.COLLIDABLE_BIT;
			fixtureDef.filter.maskBits = Game.PLAYER_BIT | Game.BULLET_BIT | Game.ENEMY_BIT;

			Body body = world.createBody(bodyDef);
			body.createFixture(fixtureDef);
			shape.dispose();
		}
	}
	
	public void getBulletCollidable(World world) {
		for (MapObject object : map.getLayers().get("bullet_collidable").getObjects()) {
			float[] vertices = ((PolygonMapObject) object).getPolygon().getTransformedVertices();
			float[] worldVertices = new float[vertices.length];
			for (int i = 0; i < vertices.length; i++)
				worldVertices[i] = vertices[i] / Game.PPM;
			
			PolygonShape shape = new PolygonShape();
			shape.set(worldVertices);
			
			BodyDef bodyDef = new BodyDef();
			bodyDef.type = BodyDef.BodyType.StaticBody;
			
			FixtureDef fixtureDef = new FixtureDef();
			fixtureDef.shape = shape;
			fixtureDef.density = 1000f;
			fixtureDef.filter.categoryBits = Game.BULLET_COLLIDABLE_BIT;
			fixtureDef.filter.maskBits = Game.BULLET_BIT;
			
			Body body = world.createBody(bodyDef);
			body.createFixture(fixtureDef);
			shape.dispose();
		}
	}
	
	public Vector2 getPlayerSpawn() {
		for (MapObject object : map.getLayers().get("player_spawn").getObjects()) {
			Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
			return new Vector2(rectangle.x / Game.PPM, rectangle.y / Game.PPM);
		}
		return new Vector2(0, 0);
	}
	
	public ArrayList<Vector2> getEnemySpawns() {
		ArrayList<Vector2> spawns = new ArrayList<Vector2>();
		for (MapObject object : map.getLayers().get("enemy_spawn").getObjects()) {
			Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
			spawns.add(new Vector2(rectangle.x / Game.PPM, rectangle.y / Game.PPM));
		}
		return spawns;
	}
	
	public ArrayList<Rectangle> getCameraInfos() {
		ArrayList<Rectangle> infos = new ArrayList<Rectangle>();
		for (MapObject object : map.getLayers().get("camera").getObjects()) {
			Rectangle rectangle = ((RectangleMapObject) object).getRectangle();
			infos.add(new Rectangle(rectangle.x / Game.PPM, rectangle.y / Game.PPM, rectangle.width / Game.PPM, rectangle.height / Game.PPM));
		}
		return infos;
	}
	
	@Override
	public void dispose() {
		map.dispose();
		mapRenderer.dispose();
	}

	public int getTileWidth() {
		return tileWidth;
	}

	public int getTileHeight() {
		return tileHeight;
	}

	public int getMapWidth() {
		return mapWidth;
	}

	public int getMapHeight() {
		return mapHeight;
	}

	public float getTransformedMapWidth() {
		return transformedMapWidth;
	}

	public float getTransformedMapHeight() {
		return transformedMapHeight;
	}
}
