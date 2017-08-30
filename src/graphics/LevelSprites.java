package graphics;

public abstract class LevelSprites {

	//CITY and FACTORY
	//Floors
	public static Sprite dirt = new Sprite(32, 0, 0, SpriteSheet.city);
	public static Sprite rocks = new Sprite(32, 0, 1, SpriteSheet.city);
	public static Sprite concreteFloor = new Sprite(32, 0, 2, SpriteSheet.city);
	public static Sprite basementFloor = new Sprite(32, 0, 3, SpriteSheet.city);
	public static Sprite junk = new Sprite(32, 0, 4, SpriteSheet.city);
	public static Sprite path = new Sprite(32, 0, 5, SpriteSheet.city);
	public static Sprite black = new Sprite(32, 0, 6, SpriteSheet.city);
	
	//Walls
	public static Sprite woodenWall = new Sprite(32, 1, 0, SpriteSheet.city);
	
	public static Sprite concreteWall = new Sprite(32, 1, 1, SpriteSheet.city);
	public static Sprite stoneWall = new Sprite(32, 1, 2, SpriteSheet.city);
	public static Sprite stoneBrickWall = new Sprite(32, 1, 3, SpriteSheet.city);
	
	public static Sprite mossBrickWall = new Sprite(32, 1, 4, SpriteSheet.city);
	
	//Portals
	public static Sprite woodenDoor = new Sprite(32, 2, 0, SpriteSheet.city);
	public static Sprite metalDoor = new Sprite(32, 2, 1, SpriteSheet.city);
	public static Sprite concreteStairs_1 = new Sprite(32, 2, 2, SpriteSheet.city);
	public static Sprite ladder = new Sprite(32, 2, 3, SpriteSheet.city);
	
	public static Sprite stoneOpening = new Sprite(32, 2, 4, SpriteSheet.city);
	public static Sprite largeWoodenDoor = new Sprite(32, 2, 5, SpriteSheet.city);
	
	//Windows
	public static Sprite houseWindow = new Sprite(32, 3, 0, SpriteSheet.city);
	public static Sprite factoryWindow = new Sprite(32, 3, 1, SpriteSheet.city);
	
	//Roofs
	public static Sprite shingles = new Sprite(32, 4, 0, SpriteSheet.city);
	public static Sprite concreteRoof = new Sprite(32, 4, 1, SpriteSheet.city);
	
	//Decorations
	public static Sprite roofPipe = new Sprite(32, 5, 0, SpriteSheet.city);
	
	//Miscellaneous - Solid
	public static Sprite crate = new Sprite(32, 6, 0, SpriteSheet.city);
	public static Sprite conveyorBelt_1 = new Sprite(32, 6, 1, SpriteSheet.city);
	public static Sprite conveyorBelt_2 = new Sprite(32, 6, 2, SpriteSheet.city);
	public static Sprite machineStorage = new Sprite(32, 6, 3, SpriteSheet.city);
	public static Sprite gate = new Sprite(32, 6, 4, SpriteSheet.city);
	
	///Miscellaneous - UnSolid
	public static Sprite bed = new Sprite(32, 7, 0, SpriteSheet.city);
	
	//Stairs / Ladders
	public static Sprite concreteStairs_2 = new Sprite(32, 8, 0, SpriteSheet.city);
	public static Sprite concreteStairs_3 = new Sprite(32, 8, 1, SpriteSheet.city);
	
	//MOUNTAINS and CAVES
	//Floors
	public static Sprite mountainPath = new Sprite(32, 0, 0, SpriteSheet.caves);
	public static Sprite desertCaveFloor = new Sprite(32, 0, 1, SpriteSheet.caves);
	public static Sprite caveFloor = new Sprite(32, 0, 2, SpriteSheet.caves);
	public static Sprite crystalFloor = new Sprite(32, 0, 3, SpriteSheet.caves);
	
	//Walls
	public static Sprite mountainWall = new Sprite(32, 1, 0, SpriteSheet.caves);
	public static Sprite desertCaveWall = new Sprite(32, 1, 1, SpriteSheet.caves);
	public static Sprite caveWall = new Sprite(32, 1, 2, SpriteSheet.caves);
	public static Sprite crystalWall = new Sprite(32, 1, 3, SpriteSheet.caves);
	
	//Portals
	public static Sprite desertCaveOpening = new Sprite(32, 2, 0, SpriteSheet.caves);
	public static Sprite caveOpening = new Sprite(32, 2, 3, SpriteSheet.caves);
	public static Sprite desertCaveExit = new Sprite(32, 2, 1, SpriteSheet.caves);
	public static Sprite caveExit = new Sprite(32, 2, 2, SpriteSheet.caves);
	
	//Misc - SOLID
	public static Sprite mountainRock = new Sprite(32, 6, 0, SpriteSheet.caves);
	public static Sprite desertStalagmite = new Sprite(32, 6, 1, SpriteSheet.caves);
	public static Sprite stalagmite = new Sprite(32, 6, 2, SpriteSheet.caves);
	public static Sprite crystal = new Sprite(32, 6, 3, SpriteSheet.caves);
	public static Sprite crystalStalagmite = new Sprite(32, 6, 4, SpriteSheet.caves);
	
	//Resting Place
	public static Sprite desertHealCircle = new Sprite(32, 7, 0, SpriteSheet.caves);
	public static Sprite healCircle = new Sprite(32, 7, 1, SpriteSheet.caves);
	
	//JUNGLES and SWAMPS
	//Floors
	public static Sprite swampGrass = new Sprite(32, 0, 0, SpriteSheet.swamp);
	public static Sprite marshGround = new Sprite(32, 0, 1, SpriteSheet.swamp);
	public static Sprite grassyWater = new Sprite(32, 0, 2, SpriteSheet.swamp);
	public static Sprite marshyWater = new Sprite(32, 0, 3, SpriteSheet.swamp);
	public static Sprite lilipad = new Sprite(32, 0, 4, SpriteSheet.swamp);
	
	//Walls
	public static Sprite jungleStone = new Sprite(32, 1, 0, SpriteSheet.swamp);
	public static Sprite treeTrunk = new Sprite(32, 1, 1, SpriteSheet.swamp);
	public static Sprite water = new Sprite(32, 1, 2, SpriteSheet.swamp);
	public static Sprite darkWater = new Sprite(32, 1, 3, SpriteSheet.swamp);
	public static Sprite marshStone = new Sprite(32, 1, 4, SpriteSheet.swamp);
	
	//Ceiling
	public static Sprite leaves = new Sprite(32, 4, 0, SpriteSheet.swamp);
	
	//Misc - SOLID
	public static Sprite swampTree = new Sprite(32, 6, 0, SpriteSheet.swamp);
	public static Sprite deadTree = new Sprite(32, 6, 1, SpriteSheet.swamp);
	public static Sprite swampBush = new Sprite(32, 6, 2, SpriteSheet.swamp);
	public static Sprite jungleRock = new Sprite(32, 6, 3, SpriteSheet.swamp);
	public static Sprite marshRock = new Sprite(32, 6, 4, SpriteSheet.swamp);
	public static Sprite campFire = new Sprite(32, 6, 5, SpriteSheet.swamp);
	public static Sprite tent = new Sprite(32, 6, 6, SpriteSheet.swamp);
	
	//BADLANDS AND BARRENS
	//Floors
	public static Sprite rocky = new Sprite(32, 0, 0, SpriteSheet.badlands);
	public static Sprite barren = new Sprite(32, 0, 1, SpriteSheet.badlands);
	
	//Walls
	public static Sprite cliff = new Sprite(32, 1, 0, SpriteSheet.badlands);
	
	//WORLD MAP
	public static Sprite orzeik = new Sprite(32, 0, 0, SpriteSheet.areas);
	
	public static Sprite trench = new Sprite(32, 1, 0, SpriteSheet.areas);
	public static Sprite swamp = new Sprite(32, 2, 0, SpriteSheet.areas);
	public static Sprite mortisia = new Sprite(32, 3, 0, SpriteSheet.areas);
	public static Sprite crystalCave = new Sprite(32, 4, 0, SpriteSheet.areas);
	
	public static Sprite dropZone = new Sprite(32, 0, 1, SpriteSheet.areas);
	public static Sprite machina = new Sprite(32, 1, 1, SpriteSheet.areas);
	public static Sprite jurgo = new Sprite(32, 2, 1, SpriteSheet.areas);
	public static Sprite crystalMine = new Sprite(32, 3, 1, SpriteSheet.areas);
	
	public static Sprite wreckedBridge = new Sprite(32, 4, 1, SpriteSheet.areas);
	public static Sprite ybrid = new Sprite(32, 0, 2, SpriteSheet.areas);
	public static Sprite gyad = new Sprite(32, 1, 2, SpriteSheet.areas);
	public static Sprite scarredShore = new Sprite(32, 2, 2, SpriteSheet.areas);
	public static Sprite radioTower = new Sprite(32, 3, 2, SpriteSheet.areas);
	
	public static Sprite borderlands = new Sprite(32, 4, 2, SpriteSheet.areas);
	public static Sprite zancrete = new Sprite(32, 0, 3, SpriteSheet.areas);
	public static Sprite moat = new Sprite(32, 1, 3, SpriteSheet.areas);
	public static Sprite bashada = new Sprite(32, 2, 3, SpriteSheet.areas);
	
	//MAP EXCLUSIVE
	public static Sprite mountains = new Sprite(32, 5, 0, SpriteSheet.areas);
	public static Sprite trenches = new Sprite(32, 5, 1, SpriteSheet.areas);
	public static Sprite trees = new Sprite(32, 5, 2, SpriteSheet.areas);
	public static Sprite barrenMountains = new Sprite(32, 5, 3, SpriteSheet.areas);
	public static Sprite scarredShore2 = new Sprite(32, 6, 0, SpriteSheet.areas);
}
