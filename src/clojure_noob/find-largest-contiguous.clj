(ns clojure-noob.core
  (:gen-class))


; Find the largest contiguous array
; http://whostolebenfrog.github.io/interviews/2014/06/27/contiguous.html

(def nums [-4 3 0 -1 -6 4 5 -2 3 -1 2 -5 4])


(defn find-largest-contiguous [nums]
  (loop [nums nums]
    (let [current-num (first nums)
          rest-nums (rest nums)]
      (println (str "num " current-num))

      (if (not (empty? rest-nums))
        (recur (rest-nums))))))


(find-largest-contiguous nums)

