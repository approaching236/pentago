(ns pentago.core
  (:gen-class))

(defprotocol Flatten
  "Can be converted from internal moveable structure to vector"
  (to_a [_]))

(defprotocol Rotate
  "Can be converted from internal moveable structure to vector"
  (rotate_tile_right [_])
  (left [_]))

(defn rotate_list_right [s]
  (cons (last s) (drop-last s)))

(defrecord Tile [ring anchor]
  Flatten
    (to_a [_] [(into [] (take 3 ring))
             [(nth ring 7) anchor (nth ring 3)]
             [(nth ring 6) (nth ring 5) (nth ring 4)]])
  Rotate
    (rotate_tile_right [_] (Tile. (rotate_list_right (rotate_list_right ring) anchor)))
    (left [_] (str "rotating left"))
  )

(defn blank_tile [] (Tile. `(nil nil nil nil nil nil nil nil) nil))
(defn test_tile [] (Tile. `(0 1 2 3 4 5 6 7) 8))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (rotate_tile_right (first args)))
