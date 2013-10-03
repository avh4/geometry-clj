(ns net.avh4.geometry.rect
  (:require [net.avh4.geometry.alignment :as alignment]))

;;; -> rect

(defn from-top-left
  ([x y w h] [x y w h])
  ([[x y] w h] (from-top-left x y w h)))

(defn from-center
  ([cx cy w h] [(- cx (/ w 2)) (- cy (/ h 2)) w h])
  ([[cx cy] w h] (from-center cx cy w h)))

(defn of-size [w h]
  [0 0 w h])

;;; rect -> rect

(defn- inset [i] [1 (* -2 i)])
(defn- outset [i] [1 (* 2 i)])
(def ^:private same [1 0])
(defn- exactly [i] [0 i])

(defn transform [[x y w h] [ax ay] [wm wk] [hm hk]]
  (let [nw (+ (* w wm) wk)
        nh (+ (* h hm) hk)]
    [(+ x (* (- w nw) ax)) (+ y (* (- h nh) ay)) nw nh]))

(defn inset [rect i]
  (transform rect alignment/center (inset i) (inset i)))

(defn outset [rect i]
  (transform rect alignment/center (outset i) (outset i)))

(defn top [rect i]
  (transform rect alignment/top same (exactly i)))

(defn left [rect i]
  (transform rect alignment/left (exactly i) same))

(defn right [rect i]
  (transform rect alignment/right (exactly i) same))

(defn bottom [rect i]
  (transform rect alignment/bottom same (exactly i)))

;;; rect -> point

(defn center [[x y w h]]
  [(+ x (/ w 2)) (+ y (/ h 2))])

(defn top-left [[x y _ _]]
  [x y])
