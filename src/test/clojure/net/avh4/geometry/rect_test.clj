(ns net.avh4.geometry.rect
  (:use [midje.sweet]
        [net.avh4.geometry.rect]))

(facts
  (fact "left gives a fixed-width left portion of a rect"
    (left (of-size 800 600) 10) => (of-size 10 600))
  (fact "top gives a fixed-height top portion of a rect"
    (top (of-size 800 600) 10) (of-size 800 10))
  (fact "inset gives a centered inset rect"
    (inset (of-size 800 600) 10) (from-top-left 10 10 780 580)))
