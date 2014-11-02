;; In ns below, notice that "gen-class" was removed
(ns fwpd.core
  ;; We haven't gone over require but we will.
  (:require [clojure.string :as s]))

(def filename "/Users/tonyvanriet/Dropbox/Development/GitHub/clojure-noob/src/clojure_noob/suspects.csv")

;; Later on we're going to be converting each row in the CSV into a
;; map, like {:name "Edward Cullen" :glitter-index 10}.
;; Since CSV can't store Clojure keywords, we need to associate the
;; textual header from the CSV with the correct keyword.
(def headers->keywords {"Name" :name
                        "Glitter Index" :glitter-index})

(defn str->int
  [str]
  (Integer. str))

;; CSV is all text, but we're storing numeric data. We want to convert
;; it back to actual numbers.
(def conversions {:name identity
                  :glitter-index str->int})

(defn parse
  "Convert a csv into rows of columns"
  [string]
  (map #(s/split % #",")
       (s/split string #"\n")))

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (let [;; headers becomes the seq (:name :glitter-index)
        headers (map #(get headers->keywords %) (first rows))
        ;; unmapped-rows becomes the seq
        ;; (["Edward Cullen" "10"] ["Bella Swan" "0"] ...)
        unmapped-rows (rest rows)]
    ;; Now let's return a seq of {:name "X" :glitter-index 10}
    (map (fn [unmapped-row]
           ;; We're going to use map to associate each header with its
           ;; column. Since map returns a seq, we use "into" to convert
           ;; it into a map.
           (into {}
                 (mapify-row headers unmapped-row)))
         unmapped-rows)))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))


(mapify (parse (slurp filename)))


(defn mapify-row
  [headers unmapped-row]
  (map (fn [header column]
         ;; associate the header with the converted column
         [header ((get conversions header) column)])
       headers
       unmapped-row))

(mapify-row [:name] ["jeff"])


(def suspects (mapify (parse (slurp filename))))

suspects

(filter #(> (:glitter-index %) 5) suspects)
(glitter-filter 5 suspects)

; filter returns list of names
(defn glitter-filter-names
  [minimum-glitter records]
  (map :name (glitter-filter minimum-glitter records)))

(glitter-filter-names 5 suspects)


(defn prepend
  [nname glitter-index records]
  (conj records {:name nname, :glitter-index glitter-index}))

(prepend "dabney" 5 suspects)


