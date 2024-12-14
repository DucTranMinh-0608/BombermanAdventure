package oop.level;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import oop.Board;
import oop.Game;
import oop.entities.LayeredEntity;
import oop.entities.character.Bomber;
import oop.entities.character.enemy.Balloon;
import oop.entities.character.enemy.Doll;
import oop.entities.character.enemy.Oneal;
import oop.entities.tile.Grass;
import oop.entities.tile.Portal;
import oop.entities.tile.Wall;
import oop.entities.tile.destroyable.Brick;
import oop.entities.tile.item.BombItem;
import oop.entities.tile.item.FlameItem;
import oop.entities.tile.item.SpeedItem;
import oop.exceptions.LoadLevelException;
import oop.graphics.Screen;
import oop.graphics.Sprite;

public class FileLevelLoader extends LevelLoader {

    /**
     * Ma trận chứa thông tin bản đồ, mỗi phần tử lưu giá trị kí tự đọc được từ
     * ma trận bản đồ trong tệp cấu hình
     */
    private static char[][] _map;

    public FileLevelLoader(Board board, int level) throws LoadLevelException {
        super(board, level);
    }

    @Override
    public void loadLevel(int level) {
        // TODO: đọc dữ liệu từ tệp cấu hình /levels/Level{level}.txt
        // TODO: cập nhật các giá trị đọc được vào _width, _height, _level, _map
        List<String> list = new ArrayList<>();
        try {
            FileReader fr = new FileReader("res\\levels\\Level" + level + ".txt");//doc tep luu map
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            while (!line.equals("")) {
                list.add(line);
                line = br.readLine();
                //doc file txt luu vao list
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] arrays = list.get(0).trim().split(" ");
        _level = Integer.parseInt(arrays[0]);
        _height = Integer.parseInt(arrays[1]);
        _width = Integer.parseInt(arrays[2]);
        _map = new char[_height][_width];
        for (int i = 0; i < _height; i++) {
            for (int j = 0; j < _width; j++) {
                _map[i][j] = list.get(i + 1).charAt(j);
            }
        }
        //gan cac phan tu cho mang
    }

    @Override
    public void createEntities() {
        // TODO: tạo các Entity của màn chơi
        // TODO: sau khi tạo xong, gọi _board.addEntity() để thêm Entity vào game

        // TODO: phần code mẫu ở dưới để hướng dẫn cách thêm các loại Entity vào game
        // TODO: hãy xóa nó khi hoàn thành chức năng load màn chơi từ tệp cấu hình
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                int pos = x + y * getWidth();
                char c = _map[y][x];
                switch (c) {
              
                    // Thêm grass
                //map 1
                    case ' ':
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass1));
                        break;
                        //map 2
                    case '+':
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass2));
                        break;
                        //map 3
                    case '-':
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass3));
                        break;
                        //map 4
                    case '<':
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass4));
                        break;
                    case '>':
                        _board.addEntity(pos, new Grass(x, y, Sprite.grassl));
                        break;
                    case '&':
                        _board.addEntity(pos, new Grass(x, y, Sprite.grassr));
                        break;
                    case '%':
                        _board.addEntity(pos, new Grass(x, y, Sprite.floor4));
                        break;
                    // Thêm Wall
                        // map 1 và 4
                    case '#':
                        _board.addEntity(pos, new Wall(x, y, Sprite.wall1));
                        break;
                        // map 2
                    case '!':
                        _board.addEntity(pos, new Wall(x, y, Sprite.wall2));
                        break;
                        //map 3
                    case '@':
                        _board.addEntity(pos, new Wall(x, y, Sprite.wall3));
                        break;
                    // Thêm Portal
                        // map 1,2,3
                    case 'x':
                        _board.addEntity(pos, new LayeredEntity(x, y,
                                new Grass(x, y, Sprite.grass1),
                                new Portal(x, y, _board, Sprite.portal)));
                        break;
                        // map 4
                    case 'o':
                        _board.addEntity(pos, new LayeredEntity(x, y,
                                new Grass(x, y, Sprite.floor4),
                                new Portal(x, y, _board, Sprite.portal4)));
                        break;
                    // Thêm brick
                    case '*':
                        _board.addEntity(x + y * _width,
                                new LayeredEntity(x, y,
                                        new Grass(x, y, Sprite.grass1),
                                        new Brick(x, y, Sprite.brick1)
                                )
                        );
                        break;
                    case '?':
                        _board.addEntity(x + y * _width,
                                new LayeredEntity(x, y,
                                        new Grass(x, y, Sprite.grass2),
                                        new Brick(x, y, Sprite.brick2)
                                )
                        );
                        break;
                    case '/':
                        _board.addEntity(x + y * _width,
                                new LayeredEntity(x, y,
                                        new Grass(x, y, Sprite.grass3),
                                        new Brick(x, y, Sprite.brick3)
                                )
                        );
                        break;
                    case ':':
                        _board.addEntity(x + y * _width,
                                new LayeredEntity(x, y,
                                        new Grass(x, y, Sprite.floor4),
                                        new Brick(x, y, Sprite.door4)
                                )
                        );
                    case ',':
                        _board.addEntity(x + y * _width,
                                new LayeredEntity(x, y,
                                        new Grass(x, y, Sprite.grass4),
                                        new Brick(x, y, Sprite.door4)
                                )
                        );
                        break;
                    // Thêm Bomber
                       
                    case 'p':
                        _board.addCharacter(new Bomber(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        Screen.setOffset(0, 0);
                        _board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass1));
                        break;

                    // Thêm balloon
                        // map 1
                    case '1':
                        _board.addCharacter(new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass1));
                        break;
                        // map 2
                    case '2':
                        _board.addCharacter(new Balloon(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass2));
                        break;
                    // Thêm oneal
                    case '3':
                        _board.addCharacter(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass2));
                        break;
                        // map 3
                    // Thêm oneal
                    case '4':
                        _board.addCharacter(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass3));
                        break;
                        // map 4
                    // Thêm oneal
                    case '5':
                        _board.addCharacter(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass4));
                        break;
                    // Thêm doll
                    case '6':
                        _board.addCharacter(new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(x + y * _width, new Grass(x, y, Sprite.grass4));
                        break;
                    case '7':
                        _board.addCharacter(new Oneal(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(pos, new Grass(x, y, Sprite.floor4));
                        break;
                    // Thêm doll
                    case '8':
                        _board.addCharacter(new Doll(Coordinates.tileToPixel(x), Coordinates.tileToPixel(y) + Game.TILES_SIZE, _board));
                        _board.addEntity(x + y * _width, new Grass(x, y, Sprite.floor4));
                        break;
                    // Thêm BomItem            
                    case 'b':
                        LayeredEntity layer = new LayeredEntity(x, y,
                                new Grass(x, y, Sprite.grass1),
                                new BombItem(x, y, Sprite.powerup_bombs),
                                new Brick(x, y, Sprite.treasure));
                        _board.addEntity(pos, layer);
                        break;
                    case 'w':
                        LayeredEntity layer1 = new LayeredEntity(x, y,
                                new Grass(x, y, Sprite.grass2),
                                new BombItem(x, y, Sprite.powerup_bombs),
                                new Brick(x, y, Sprite.treasure));
                        _board.addEntity(pos, layer1);
                        break;
                    case 'e':
                        LayeredEntity layer2= new LayeredEntity(x, y,
                                new Grass(x, y, Sprite.grass3),
                                new BombItem(x, y, Sprite.powerup_bombs),
                                new Brick(x, y, Sprite.treasure));
                        _board.addEntity(pos, layer2);
                        break;
                    // Thêm SpeedItem
                    case 's':
                        layer = new LayeredEntity(x, y,
                                new Grass(x, y, Sprite.grass1),
                                new SpeedItem(x, y, Sprite.powerup_speed),
                                new Brick(x, y, Sprite.treasure));
                        _board.addEntity(pos, layer);
                        break;
                    case 'r':
                        layer = new LayeredEntity(x, y,
                                new Grass(x, y, Sprite.grass2),
                                new SpeedItem(x, y, Sprite.powerup_speed),
                                new Brick(x, y, Sprite.treasure));
                        _board.addEntity(pos, layer);
                        break;
                    case 't':
                        layer = new LayeredEntity(x, y,
                                new Grass(x, y, Sprite.grass3),
                                new SpeedItem(x, y, Sprite.powerup_speed),
                                new Brick(x, y, Sprite.treasure));
                        _board.addEntity(pos, layer);
                        break;
                    // Thêm FlameItem
                    case 'f':
                        layer = new LayeredEntity(x, y,
                                new Grass(x, y, Sprite.grass1),
                                new FlameItem(x, y, Sprite.powerup_flames),
                                new Brick(x, y, Sprite.treasure));
                        _board.addEntity(pos, layer);
                        break;
                    case 'y':
                        layer = new LayeredEntity(x, y,
                                new Grass(x, y, Sprite.grass2),
                                new FlameItem(x, y, Sprite.powerup_flames),
                                new Brick(x, y, Sprite.treasure));
                        _board.addEntity(pos, layer);
                        break;
                    case 'u':
                        layer = new LayeredEntity(x, y,
                                new Grass(x, y, Sprite.grass3),
                                new FlameItem(x, y, Sprite.powerup_flames),
                                new Brick(x, y, Sprite.treasure));
                        _board.addEntity(pos, layer);
                        break;

                    default:
                        _board.addEntity(pos, new Grass(x, y, Sprite.grass1));
                        break;

                }
            }
        }
    }
}
