(ns clojure-noob.core
  (:gen-class))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))



;
; Clojure for the Brave and True notes
;

(def noob-map
  {:a "the key a"
   :b 2
   :c [1 2 3]})

(noob-map :a)

(:a noob-map)

(:b noob-map)

(or 1 2)

((or + -) 1 2 3)

; ((and + 0) 1 2 3)


(map even? [1 2 3 4 5])
; map returns a list, regardless of input


; can't use special forms as arguments to functions
(+
  (if true
    1
    2)
  (if false
    5
    6))
; doesn't seem to be true. I must be misunderstanding


; destructuring

(defn noob-fn-destructuring-vector
  [first-val second-val & the-rest-of-the-vals]
   first-val)

(noob-fn-destructuring-vector 1 2 3 4)


(defn noob-fn-destructuring-map
  [{lat :lat
    lng :lng}]
  (str "up " lat " and over " lng))

(noob-fn-destructuring-map {:lat 12 :lng 54})

(defn noob-fn-destructuring-map-shortcut
  [{:keys [lat lng] :as coords}]
  (str "up " lat " and over " lng))

(noob-fn-destructuring-map-shortcut {:lat 33 :lng 55})


; anonymous functions

(map (fn [[a b]] (+ a b))
     [[1 2] [3 4] [5 6]])

(def add-two-things (fn [[a b]] (+ a b)))
(add-two-things [5 6])

; compact anonymous function syntax, unreadable, only for small functions
(#(+ %1 %2) 2 3)


; returning a function
(defn inc-maker
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))
(inc3 4)


; into, conj

(into [] {:a 1, :b 2, :c 3})
(into {} [[1 2]])
(into '() [1 2])

(into [1 2] [3 4])
(conj [1 2] 5 6)

(defn my-conj
  [to-seq & rest-params]
  (into to-seq rest-params))

(my-conj [1 2] 3 4)


; apply, partial
(apply + [1 2 3])
; apply explodes a seq so it can be passed to a function that wants a rest param

(defn my-into
  [to-seq from-seq]
  (apply conj to-seq from-seq))

(my-into [1 2] [3 4])




