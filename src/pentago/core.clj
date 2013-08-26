(ns pentago.core
  (:gen-class))

(defprotocol Flatten
  "Can be converted from internal moveable structure to vector"
  (to_a [_])
  )

(defn rotate_list_right [s]
  (concat (list (last s)) (butlast s))
  )

(defn rotate_list_left [s]
  (concat (rest s) (list (first s)))
  )

(defrecord Tile [ring anchor]
  Flatten
    (to_a [_] [(into [] (take 3 ring))
             [(nth ring 7) anchor (nth ring 3)]
             [(nth ring 6) (nth ring 5) (nth ring 4)]])
  )

(defn rotate_tile_right [s]
  (Tile. (rotate_list_right (rotate_list_right (:ring s)))
         (:anchor s))
  )

(defn rotate_tile_left [s]
  (Tile. (rotate_list_left (rotate_list_left (:ring s)))
         (:anchor s))
  )

(defn blank_tile [] (Tile. `(nil nil nil nil nil nil nil nil) nil))
(defn test_tile [] (Tile. `(0 1 2 3 4 5 6 7) 8))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (rotate_tile_right (first args))
  )

