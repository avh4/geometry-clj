(ns net.avh4.geometry.rect
  (:use [net.avh4.geometry.alignment]))

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

(defn- INSET [i] [1 (* -2 i)])
(defn- OUTSET [i] [1 (* 2 i)])
(def ^:private SAME [1 0])
(defn- EXACTLY [i] [0 i])

(defn transform [[x y w h] [ax ay] [wm wk] [hm hk]]
  (let [nw (+ (* w wm) wk)
        nh (+ (* h hm) hk)]
    [(+ x (* (- w nw) ax)) (+ y (* (- h nh) ay)) nw nh]))

(defn inset [rect i]
  (transform rect CENTER (INSET i) (INSET i)))

(defn outset [rect i]
  (transform rect CENTER (OUTSET i) (OUTSET i)))

(defn top [rect i]
  (transform rect TOP SAME (EXACTLY i)))

(defn left [rect i]
  (transform rect LEFT (EXACTLY i) SAME))

(defn right [rect i]
  (transform rect RIGHT (EXACTLY i) SAME))

(defn bottom [rect i]
  (transform rect BOTTOM SAME (EXACTLY i)))

;;; rect -> point

(defn center [[x y w h]]
  [(+ x (/ w 2)) (+ y (/ h 2))])

(defn top-left [[x y _ _]]
  [x y])
