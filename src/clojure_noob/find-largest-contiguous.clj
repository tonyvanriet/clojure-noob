(ns clojure-noob.core
  (:gen-class))



; Find the largest contiguous array
; http://whostolebenfrog.github.io/interviews/2014/06/27/contiguous.html


(def nums [-4 3 0 -1 -6 4 5 -2 3 -1 2 -5 4])


(defn find-largest-contiguous [nums]

  (loop [nums nums
         previous-stats {:max 0, :count 0, :start-index 0}
         best-stats {:max 0, :count 0, :start-index 0}]

    (let [current-num (first nums)
          rest-nums (rest nums)]

      (if (nil? current-num)

        ; when we're all out of nums, return the best results up through the recursion
        best-stats

        (do

          (def current-stats {:max (+ (:max previous-stats) current-num)
                              :count (inc (:count previous-stats))
                              :start-index (:start-index previous-stats)})

          (if (<= (:max current-stats) 0)

            ; if the max has dropped to 0 or below, reset the running stats
            (recur rest-nums
                   {:max 0, :count 0,
                    :start-index (+ (:start-index current-stats) (:count current-stats))}
                   best-stats)

            (if (> (:max current-stats) (:max best-stats))
              (recur rest-nums current-stats current-stats)
              (recur rest-nums current-stats best-stats))))))))



 (find-largest-contiguous nums)


