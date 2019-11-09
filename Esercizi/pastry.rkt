;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname dolcetti) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
(define pastries_disposal    ; val: natural number
  (lambda (pastries plates)  ; both as val
    (cond ((< pastries plates)
           0
           )
          ((or (= pastries 1) (= plates 1))
           1
           )
          (else
           (+
            (pastries_disposal (- pastries 1) (- plates 1))
            (* plates (pastries_disposal (- pastries 1) plates))
            )
           )
          )
    ))

(pastries_disposal 6 3) ; 90